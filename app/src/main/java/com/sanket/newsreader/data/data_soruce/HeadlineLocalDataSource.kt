package com.sanket.newsreader.data.data_soruce

import com.sanket.newsreader.data.models.Article

interface HeadlineLocalDataSource {
    suspend fun getBookmarkedArticles():List<Article>
}