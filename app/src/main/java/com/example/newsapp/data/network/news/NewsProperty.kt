package com.example.newsapp.data.network.news

import androidx.annotation.IntRange
import com.squareup.moshi.Json

data class NewsProperty(
    val status: String,
    @IntRange(from = 1) val totalResults: Int,
    @Json(name = "message") val message: String? = null,
    val articles: List<ArticlesNews>
) {
}