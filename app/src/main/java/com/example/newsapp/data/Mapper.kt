package com.example.newsapp.data

import com.example.newsapp.data.model.ArticlesData
import com.example.newsapp.data.model.SourceData
import com.example.newsapp.data.network.news.ArticlesNews
import com.example.newsapp.data.network.news.SourceNews

internal fun ArticlesNews.toArticlesData(): ArticlesData {
    return ArticlesData(
//        source = source?.toSource(),
        author= author,
        title = title,
        description = description,
        url = url,
        imgUrl = imgUrl,
        publishedAt = publishedAt,
        content = content
    )
}

private fun SourceNews.toSource(): SourceData? {
    return SourceData(id = id, sourceName = sourceName)

}
