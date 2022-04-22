package com.example.newsapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.data.model.ArticlesData

@Database(entities = [ArticlesData::class], version = 1, exportSchema = false)
abstract class FavoritesDatabase : RoomDatabase() {

    abstract val favoritesDatabaseDao: FavoritesDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: FavoritesDatabase? = null

        fun getInstance(context: Context): FavoritesDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavoritesDatabase::class.java,
                        "favorite_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }

                return instance
            }
        }
    }

}