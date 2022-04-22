package com.example.newsapp.data.source

import androidx.lifecycle.LiveData
import com.example.newsapp.data.model.ArticlesData

interface FavoritesRepository {
    suspend fun saveFavorite(favorite: ArticlesData)

    suspend fun deleteFavorite(favoriteId: Long) : Int

    suspend fun clearAllFavoritesData()

    suspend fun getAllFavorites(): List<ArticlesData>

    suspend fun getFavorite(favoriteId: Long): ArticlesData
}