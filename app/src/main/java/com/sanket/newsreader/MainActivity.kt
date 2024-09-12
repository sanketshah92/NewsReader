package com.sanket.newsreader

import android.content.Context
import android.os.Bundle
import android.print.PrintAttributes.Margins
import android.util.Log
import android.widget.ProgressBar
import android.widget.Space
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import coil.compose.AsyncImage
import com.sanket.newsreader.data.models.Article
import com.sanket.newsreader.ui.di.core.Injector
import com.sanket.newsreader.ui.theme.NewsReaderTheme
import com.sanket.newsreader.ui.viewmodel.NewsReaderViewModel
import com.sanket.newsreader.ui.viewmodel.NewsReaderViewModelFactory
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var newsReaderViewModelFactory: NewsReaderViewModelFactory

    lateinit var newsReaderViewModel: NewsReaderViewModel

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as Injector).createHeadlineSubcomponent().inject(this)
        newsReaderViewModel =
            ViewModelProvider(this, newsReaderViewModelFactory)[NewsReaderViewModel::class]
        newsReaderViewModel.getHeadLines("sports")

        enableEdgeToEdge()

        setContent {


            val navController = rememberNavController()
            NavHost(navController, startDestination = "headline_page") {
                composable("headline_page") {
                    HeadlinePage(
                        navController,
                        this@MainActivity,
                        newsReaderViewModel
                    )
                }
                composable<Article> { navBackStackEntry ->
                    NewsDetailsPage(navBackStackEntry.toRoute<Article>())
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeadlinePage(
    navController: NavController,
    context: Context,
    newsReaderViewModel: NewsReaderViewModel
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val headlineUiState by newsReaderViewModel.uiState.collectAsState()
    NewsReaderTheme {
        Scaffold(modifier = Modifier
            .fillMaxSize(), topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                scrollBehavior = scrollBehavior,
                title = { Text("Headlines of the day") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Red)
            )
        }) { innerPadding ->

            Column(modifier = Modifier.padding(top = 40.dp)) {
                CategoryDropDown(newsReaderViewModel)
                if (headlineUiState.isLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                } else if (headlineUiState.hasError) {
                    Toast.makeText(
                        context,
                        "Something went wrong, Try again later!",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    headlineUiState.articles?.let {
                        HeadLinesLazyList(
                            navController,
                            it, modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun HeadLinesLazyList(navController: NavController, articles: List<Article>, modifier: Modifier) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = articles) { article ->
            NewsTileItem(navController, article)
        }
    }
}

@Composable
fun NewsTileItem(navController: NavController, article: Article) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(3.dp, Color.DarkGray),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.clickable {
                navController.navigate(article)
        }) {
            AsyncImage(
                model = if (!article.urlToImage.isNullOrEmpty()) article.urlToImage else "https://picsum.photos/1080/600",
                contentDescription = "Headline: ${article.title}"
            )
            Text(
                modifier = Modifier.padding(8.dp),
                maxLines = 2,
                text = article.title,
                fontSize = 16.sp,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Composable
fun CategoryDropDown(viewModel: NewsReaderViewModel) {
    // Declaring a boolean value to store
    // the expanded state of the Text Field
    var mExpanded by remember { mutableStateOf(false) }

    // Create a list of cities
    val mCities =
        listOf("business", "entertainment", "general", "health", "science", "sports", "technology")

    // Create a string value to store the selected city
    var mSelectedText by remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(20.dp)) {

        // Create an Outlined Text Field
        // with icon and not expanded
        OutlinedTextField(
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = { Text("Category") },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { mExpanded = !mExpanded })
            }
        )

        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            mCities.forEach { label ->
                DropdownMenuItem(onClick = {
                    mSelectedText = label
                    mExpanded = false
                    viewModel.getHeadLines(label)
                }, text = { Text(label) })
            }
        }
    }
}

@Composable
fun NewsDetailsPage(article: Article) {
    Column {
        Spacer(modifier = Modifier.padding(top = 50.dp))
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth().fillMaxHeight(),
            elevation = CardDefaults.cardElevation(10.dp),
            shape = RoundedCornerShape(12.dp),

            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column() {
                AsyncImage(
                    model = if (!article.urlToImage.isNullOrEmpty()) article.urlToImage else "https://picsum.photos/1080/600",
                    contentDescription = "Headline: ${article.title}"
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    maxLines = 2,
                    text = article.title,
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = article.description,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.W600
                )
            }
        }
    }
}
