package com.sanket.newsreader.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.sanket.newsreader.domain.GetBookMarksUseCase
import com.sanket.newsreader.domain.GetHeadlineUseCase
import com.sanket.newsreader.ui.ui_states.HeadlineUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NewsReaderViewModel(
    private val getHeadlineUseCase: GetHeadlineUseCase,
    private val getBookMarksUseCase: GetBookMarksUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HeadlineUIState())
    val uiState: StateFlow<HeadlineUIState> = _uiState.asStateFlow()
    fun getHeadLines(category: String) {

        _uiState.update { currentState ->
            currentState.copy(isLoading = true, articles = null, hasError = false)
        }
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val resposne = getHeadlineUseCase.execute(category)
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        articles = resposne?.articles,
                        hasError = false
                    )
                }
            }
        }


        /*return liveData {
            val headline = getHeadlineUseCase.execute(category)
            headline?.let {
                updatedHeadline = if (it.articles.isEmpty()) {
                    headlineUIState.copy(
                        isLoading = false,
                        articles = it.articles,
                        hasError = false
                    )
                } else {
                    headlineUIState.copy(isLoading = false, articles = null, hasError = true)
                }
                emit(updatedHeadline)
            }
        }*/
    }

    fun getBookMarks() = liveData {
        val bookMarks = getBookMarksUseCase.execute()
        emit(bookMarks)
    }
}