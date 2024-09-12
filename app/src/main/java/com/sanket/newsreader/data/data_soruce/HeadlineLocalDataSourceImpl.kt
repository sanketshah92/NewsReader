package com.sanket.newsreader.data.data_soruce

import com.sanket.newsreader.data.db.BookmarkArticleDAO
import com.sanket.newsreader.data.models.Article

class HeadlineLocalDataSourceImpl(private val dao: BookmarkArticleDAO):HeadlineLocalDataSource {
    override suspend fun getBookmarkedArticles(): List<Article> {
        return dao.getBookmarkedArticles()
    }
}