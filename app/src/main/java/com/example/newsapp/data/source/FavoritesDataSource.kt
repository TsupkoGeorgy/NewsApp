package com.example.newsapp.data.source

import androidx.lifecycle.LiveData
import com.example.newsapp.data.model.ArticlesData

interface FavoritesDataSource {

    suspend fun saveFavorite(favorite: ArticlesData)

    suspend fun deleteFavoritesById(favoriteId: Long) : Int

    suspend fun clearAllFavoritesData()

    suspend fun getAllFavorites(): List<ArticlesData>

    suspend fun getFavoriteWithId(key: Long): ArticlesData
}