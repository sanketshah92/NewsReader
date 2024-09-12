package com.sanket.newsreader.ui.di.core

import android.content.Context
import androidx.room.Room
import com.sanket.newsreader.data.db.BookmarkArticleDAO
import com.sanket.newsreader.data.db.BookmarkDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideBookmarkDB(context: Context): BookmarkDatabase {
        return Room.databaseBuilder(context, BookmarkDatabase::class.java, "bookmark_db").build()
    }
    @Provides
    fun provideBoomarkDao(bookmarkDatabase: BookmarkDatabase):BookmarkArticleDAO{
        return bookmarkDatabase.bookmarkeArticleDao()
    }
}