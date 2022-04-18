package com.example.newsapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.newsapp.data.model.ArticlesData

@Dao
interface FavoriteDatabaseDao {

    @Insert
    fun insert(favorite:ArticlesData)

    @Update
    fun update(favorite:ArticlesData)

    @Query(value = "DELETE FROM favorites_database")
    fun clear()

    @Query(value = "SELECT * From favorites_database ORDER BY id DESC")
    fun getAllFavorites(): LiveData<List<ArticlesData>>

    @Query("SELECT * FROM favorites_database WHERE id = :key")
    fun getFavoriteWithId(key: Long): LiveData<ArticlesData>
}