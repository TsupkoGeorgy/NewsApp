package com.example.newsapp.data.database

import androidx.lifecycle.LiveData
import com.example.newsapp.data.model.ArticlesData
import com.example.newsapp.data.source.FavoritesDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteLocalDataSource internal constructor(
    private val favoritesDao: FavoritesDatabaseDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : FavoritesDataSource{
    override suspend fun saveFavorite(favorite: ArticlesData) = withContext(ioDispatcher) {
        favoritesDao.insertFavorite(favorite)
    }

    override suspend fun deleteFavoritesById(favoriteId: Long): Int  = withContext(ioDispatcher) {
       favoritesDao.deleteFavoritesById(favoriteId)
    }

    override suspend fun clearAllFavoritesData() = withContext(ioDispatcher) {
        favoritesDao.clearAllFavoritesData()
    }

    override suspend fun getAllFavorites(): List<ArticlesData> = withContext(ioDispatcher) {
        favoritesDao.getAllFavorites()
    }

    override suspend fun getFavoriteWithId(favoriteId: Long): ArticlesData = withContext(ioDispatcher) {
        favoritesDao.getFavoriteWithId(favoriteId)
    }
}