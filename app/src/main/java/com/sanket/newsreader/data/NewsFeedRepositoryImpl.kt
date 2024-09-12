package com.sanket.newsreader.data

import com.sanket.newsreader.data.data_soruce.HeadlineLocalDataSource
import com.sanket.newsreader.data.data_soruce.HeadlineRemoteDataSource
import com.sanket.newsreader.data.models.Article
import com.sanket.newsreader.data.models.Headlines
import com.sanket.newsreader.domain.NewsFeedRepository

class NewsFeedRepositoryImpl(
    private val headlineRemoteDataSource: HeadlineRemoteDataSource,
    private val headlineLocalDataSource: HeadlineLocalDataSource
) : NewsFeedRepository {
    override suspend fun getHeadlines(category: String): Headlines? {
        return headlineRemoteDataSource.getHeadlines(category, "fa02099a4fdc46c4b111bcf0dd37507f")

    }

    override suspend fun getBookmarkedArticles(): List<Article> {
        return headlineLocalDataSource.getBookmarkedArticles()
    }
}