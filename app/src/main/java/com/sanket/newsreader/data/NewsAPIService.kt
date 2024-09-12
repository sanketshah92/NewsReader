package com.sanket.newsreader.data

import com.sanket.newsreader.data.models.Headlines
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NewsAPIService {
    @GET("top-headlines")
    suspend fun getNewsFeeds(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Response<Headlines>
}