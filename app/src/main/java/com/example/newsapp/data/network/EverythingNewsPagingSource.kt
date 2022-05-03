package com.example.newsapp.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.model.ArticlesData
import com.example.newsapp.data.toArticlesData
import retrofit2.HttpException

class EverythingNewsPagingSource(
    private val newsApiService: NewsApiService,
    private val category: String,
    private val country: String,
    private val language: String,
) : PagingSource<Int, ArticlesData>() {

    override fun getRefreshKey(state: PagingState<Int, ArticlesData>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesData> {
        if (category.isEmpty()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }
        try {
            val pageNumber: Int = params.key ?: INITIAL_PAGE_NUMBER
            val pageSize: Int = params.loadSize.coerceAtMost(10)

            //val response = newsApiService.getEverythingPropertiesAsync(query, pageSize, pageNumber, language)
            val response =
                newsApiService.getTopHeadlinesPropertiesAsync(category, country, language)
            return when {
                response.isSuccessful -> {
                    val articles = response.body()!!.articles.map{ it.toArticlesData()}
                    val nextKey = if (articles.size < pageSize) null else pageNumber + 1
                    val prefKey = if (pageNumber == 1) null else pageNumber - 1
                    LoadResult.Page(articles, prefKey, nextKey)
                }
                else -> {
                    LoadResult.Error(HttpException(response))
                }
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object {

        const val INITIAL_PAGE_NUMBER = 1
    }
}