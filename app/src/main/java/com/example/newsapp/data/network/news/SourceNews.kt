package com.example.newsapp.data.network.news

import com.squareup.moshi.Json

data class SourceNews(
    val id: String?,
    @Json(name = "name") val sourceName: String,
)
{

}
