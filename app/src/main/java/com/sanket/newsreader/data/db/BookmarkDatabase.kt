package com.sanket.newsreader.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanket.newsreader.data.models.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class BookmarkDatabase:RoomDatabase() {
    abstract fun bookmarkeArticleDao(): BookmarkArticleDAO
}