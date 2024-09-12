package com.sanket.newsreader.domain

import com.sanket.newsreader.data.models.Article

class GetBookMarksUseCase(private val repository: NewsFeedRepository) {
    suspend fun execute(): List<Article> = repository.getBookmarkedArticles()
}