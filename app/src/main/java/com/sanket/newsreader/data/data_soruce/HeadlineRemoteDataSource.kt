package com.sanket.newsreader.data.data_soruce

import com.sanket.newsreader.data.models.Headlines

interface HeadlineRemoteDataSource {
    suspend fun getHeadlines(category: String, apiKey: String): Headlines?
}