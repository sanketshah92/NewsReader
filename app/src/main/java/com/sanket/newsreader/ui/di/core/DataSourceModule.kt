package com.sanket.newsreader.ui.di.core

import com.sanket.newsreader.data.NewsAPIService
import com.sanket.newsreader.data.data_soruce.HeadlineLocalDataSource
import com.sanket.newsreader.data.data_soruce.HeadlineLocalDataSourceImpl
import com.sanket.newsreader.data.data_soruce.HeadlineRemoteDataSource
import com.sanket.newsreader.data.data_soruce.HeadlineRemoteDataSourceImpl
import com.sanket.newsreader.data.db.BookmarkDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule{
   @Singleton
   @Provides
   fun providesHeadlineRemoteDataSource( apiService: NewsAPIService):HeadlineRemoteDataSource{
       return HeadlineRemoteDataSourceImpl(apiService)
   }

    @Singleton
    @Provides
    fun provideHeadlineLocalDataSource(database: BookmarkDatabase):HeadlineLocalDataSource{
        return HeadlineLocalDataSourceImpl(database.bookmarkeArticleDao())
    }

}