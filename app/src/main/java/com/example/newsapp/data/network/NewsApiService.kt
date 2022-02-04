package com.example.newsapp.data.network

import com.example.androidcentranewsapp.data.network.news.NewsProperty
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://newsapi.org/v2/"

private const val KEY = "e7a4d3493ec84a1a9232789bf7a943cf"

//
private val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val client: OkHttpClient = OkHttpClient.Builder()
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(client)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


interface NewsApiService
{
    @GET("everything?apiKey=d58241590d5d4819a0be35c1bacd2754")
    suspend fun getEverythingPropertiesAsync(
        @Query("q") category: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("language") language: String
    ): Response<NewsProperty>

}

object NewsApi
{
    val retrofitService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}
