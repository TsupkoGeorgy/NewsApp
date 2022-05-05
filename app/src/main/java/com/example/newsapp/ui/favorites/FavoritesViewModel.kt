package com.example.newsapp.ui.favorites

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.database.FavoritesDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class FavoritesViewModel(
    val dataSource: FavoritesDatabaseDao,
    application: Application,
) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    val favorites = dataSource.getAllFavorites()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}