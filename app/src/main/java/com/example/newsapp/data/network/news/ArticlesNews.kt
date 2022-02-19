package com.example.newsapp.data.network.news


import com.example.newsapp.data.network.news.SourceNews
import com.squareup.moshi.Json


data class ArticlesNews(
    val source: SourceNews,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    @Json(name = "urlToImage") val imgUrl: String?,
    val publishedAt: String,
    val content: String?,
)
{

}
