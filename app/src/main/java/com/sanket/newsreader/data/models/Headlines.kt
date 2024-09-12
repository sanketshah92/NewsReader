package com.sanket.newsreader.data.models

data class Headlines(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)