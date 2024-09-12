package com.sanket.newsreader.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sanket.newsreader.data.models.Article


@Dao
interface BookmarkArticleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookMarkArticle(article:Article)

    @Query("SELECT * FROM article")
    suspend fun getBookmarkedArticles():List<Article>
}