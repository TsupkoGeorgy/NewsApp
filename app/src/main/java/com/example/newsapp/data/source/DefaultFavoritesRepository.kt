package com.example.newsapp.data.source

import androidx.lifecycle.LiveData
import com.example.newsapp.data.database.FavoriteLocalDataSource
import com.example.newsapp.data.model.ArticlesData
import kotlinx.coroutines.*

class DefaultFavoritesRepository(
    private val favoritesLocalDataSource: FavoriteLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : FavoritesRepository {

    override suspend fun saveFavorite(favorite: ArticlesData) {
        coroutineScope {
            launch { favoritesLocalDataSource.saveFavorite(favorite) }
        }
    }

    override suspend fun getAllFavorites(): List<ArticlesData> {
        return withContext(ioDispatcher) {
            favoritesLocalDataSource.getAllFavorites()
        }
    }

    override suspend fun clearAllFavoritesData() {
        withContext(ioDispatcher) {
            coroutineScope {
                favoritesLocalDataSource.clearAllFavoritesData()
            }
        }
    }

    override suspend fun getFavorite(favoriteId: Long): ArticlesData {
        return favoritesLocalDataSource.getFavoriteWithId(favoriteId)
    }

    override suspend fun deleteFavorite(favoriteId: Long): Int {
        return favoritesLocalDataSource.deleteFavoritesById(favoriteId)
    }
}
