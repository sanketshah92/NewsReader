package com.sanket.newsreader.ui.di.core

import com.sanket.newsreader.domain.GetBookMarksUseCase
import com.sanket.newsreader.domain.GetHeadlineUseCase
import com.sanket.newsreader.domain.NewsFeedRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UsecaseModule {
    @Singleton
    @Provides
    fun provideGetHeadlineUseCase(newsFeedRepository: NewsFeedRepository): GetHeadlineUseCase {
        return GetHeadlineUseCase(newsFeedRepository)
    }

    @Singleton
    @Provides
    fun provideGetBookmarksUseCase(newsFeedRepository: NewsFeedRepository): GetBookMarksUseCase {
        return GetBookMarksUseCase(newsFeedRepository)
    }

}