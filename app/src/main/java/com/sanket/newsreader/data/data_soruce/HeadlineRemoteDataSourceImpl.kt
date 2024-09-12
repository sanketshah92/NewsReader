package com.sanket.newsreader.data.data_soruce

import com.sanket.newsreader.data.NewsAPIService
import com.sanket.newsreader.data.models.Headlines

class HeadlineRemoteDataSourceImpl(private val newsAPIService: NewsAPIService): HeadlineRemoteDataSource {
    override suspend fun getHeadlines(category: String, apiKey: String): Headlines? {
        return newsAPIService.getNewsFeeds(category,apiKey).body()
    }

}