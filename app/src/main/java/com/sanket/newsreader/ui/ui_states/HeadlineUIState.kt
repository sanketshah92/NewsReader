package com.sanket.newsreader.ui.ui_states

import com.sanket.newsreader.data.models.Article

data class HeadlineUIState(
    val isLoading: Boolean = false,
    val articles: List<Article>? = null,
    val hasError: Boolean = false
)
