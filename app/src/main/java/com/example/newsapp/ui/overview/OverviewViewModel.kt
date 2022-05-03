package com.example.newsapp.ui.overview

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.newsapp.data.database.FavoritesDatabaseDao
import com.example.newsapp.data.model.ArticlesData
import com.example.newsapp.data.network.EverythingNewsPagingSource
import com.example.newsapp.data.network.NewsApi

class OverviewViewModel(
    val database: FavoritesDatabaseDao,
    application: Application
) : ViewModel() {

    private val _filterUpdate = MutableLiveData<Boolean>()
    val filterUpdate: LiveData<Boolean>
        get() = _filterUpdate

    private val _category = MutableLiveData<String>("general")

    private val _language = MutableLiveData<String>("en")

    private val _navigateToSelectedProperty = MutableLiveData<ArticlesData?>()
    val navigateToSelectedProperty: LiveData<ArticlesData?>
        get() = _navigateToSelectedProperty

    val pagingData: LiveData<PagingData<ArticlesData>> = Pager(
        PagingConfig(pageSize = 5)
    ) {
        EverythingNewsPagingSource(
            NewsApi.retrofitService,
            _category.value!!,
            country = "us",
            _language.value!!
        )
    }.liveData.cachedIn(viewModelScope)

    fun displayPropertyDetails(articlesData: ArticlesData) {
        _navigateToSelectedProperty.value = articlesData
    }

    fun displayWebViewComplete() {
        _navigateToSelectedProperty.value = null
    }

    fun updateCategory(category: String) {
        _category.value = category
        _filterUpdate.value = true
    }

    fun updateCategoryComplete() {
        _filterUpdate.value = false
    }


    fun updateLanguage(filter: String) {
        _language.value = filter
        _filterUpdate.value = true
    }

    fun updateFilterComplete() {
        _filterUpdate.value = false
    }
}