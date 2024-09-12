package com.sanket.newsreader.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sanket.newsreader.domain.GetBookMarksUseCase
import com.sanket.newsreader.domain.GetHeadlineUseCase

class NewsReaderViewModelFactory(
    private val getHeadlineUseCase: GetHeadlineUseCase,
    private val getBookMarksUseCase: GetBookMarksUseCase
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsReaderViewModel(getHeadlineUseCase,getBookMarksUseCase) as T
    }
}