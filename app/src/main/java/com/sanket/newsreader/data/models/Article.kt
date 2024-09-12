package com.sanket.newsreader.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = "article")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val articleId:Int,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    /*val source: Source,*/
    val title: String,
    val url: String,
    val urlToImage: String?
)