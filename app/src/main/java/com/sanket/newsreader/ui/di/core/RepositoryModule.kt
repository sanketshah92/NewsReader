package com.sanket.newsreader.ui.di.core

import com.sanket.newsreader.data.NewsFeedRepositoryImpl
import com.sanket.newsreader.data.data_soruce.HeadlineLocalDataSource
import com.sanket.newsreader.data.data_soruce.HeadlineRemoteDataSource
import com.sanket.newsreader.domain.NewsFeedRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        headlineRemoteDataSource: HeadlineRemoteDataSource,
        headlineLocalDataSource: HeadlineLocalDataSource
    ): NewsFeedRepository {
        return NewsFeedRepositoryImpl(headlineRemoteDataSource, headlineLocalDataSource)
    }
}