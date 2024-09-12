package com.sanket.newsreader.domain

import com.sanket.newsreader.data.models.Headlines

class GetHeadlineUseCase(private val repository: NewsFeedRepository) {
    suspend fun execute(category:String):Headlines? = repository.getHeadlines(category)
}