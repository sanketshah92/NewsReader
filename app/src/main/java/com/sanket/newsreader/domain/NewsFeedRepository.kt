package com.sanket.newsreader.domain

import com.sanket.newsreader.data.models.Article
import com.sanket.newsreader.data.models.Headlines

interface NewsFeedRepository {
    suspend fun getHeadlines(category: String): Headlines?
    suspend fun getBookmarkedArticles(): List<Article>
}