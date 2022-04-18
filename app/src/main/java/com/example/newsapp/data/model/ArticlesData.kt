package com.example.newsapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorites_database")
data class ArticlesData(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val source: SourceData,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    val imgUrl: String?,
    val publishedAt: String,
    val content: String,
) {
}