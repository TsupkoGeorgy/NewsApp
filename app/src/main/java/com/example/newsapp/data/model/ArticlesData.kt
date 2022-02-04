package com.example.newsapp.data.model


data class ArticlesData(
    val source: SourceData,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    val imgUrl: String?,
    val publishedAt: String,
    val content: String
) {
}