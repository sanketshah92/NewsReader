package com.sanket.newsreader.ui.di.headlines

import com.sanket.newsreader.domain.GetBookMarksUseCase
import com.sanket.newsreader.domain.GetHeadlineUseCase
import com.sanket.newsreader.ui.viewmodel.NewsReaderViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class HeadlineModule {
    @HeadlineScope
    @Provides
    fun provideHeadlineNewsFactory(getHeadlineUseCase: GetHeadlineUseCase,getBookMarksUseCase: GetBookMarksUseCase):NewsReaderViewModelFactory{
        return NewsReaderViewModelFactory(getHeadlineUseCase,getBookMarksUseCase)
    }
}