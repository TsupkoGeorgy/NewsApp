package com.example.newsapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.newsapp.data.model.ArticlesData

@Dao
interface FavoritesDatabaseDao {

    @Insert
    suspend fun insertFavorite(favorite:ArticlesData)

    @Update
    suspend fun updateFavorite(favorite:ArticlesData)

    @Query("DELETE FROM favorites_database WHERE id = :favoriteId" )
    suspend fun deleteFavoritesById(favoriteId: Long) : Int

    @Query(value = "DELETE FROM favorites_database")
    suspend fun clearAllFavoritesData()

    @Query(value = "SELECT * From favorites_database ORDER BY id DESC")
    fun getAllFavorites(): List<ArticlesData>

    @Query("SELECT * FROM favorites_database WHERE id = :key")
    suspend fun getFavoriteWithId(key: Long): ArticlesData
}