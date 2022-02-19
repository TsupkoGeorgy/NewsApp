package com.example.newsapp.ui.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.newsapp.data.network.EverythingNewsPagingSource
import com.example.newsapp.data.network.NewsApi
import com.example.newsapp.data.network.news.ArticlesNews

class OverviewViewModel : ViewModel() {


    private val _filterUpdate = MutableLiveData<Boolean>()
    val filterUpdate: LiveData<Boolean>
        get() = _filterUpdate


    private val _category = MutableLiveData<String>()
    private val category: LiveData<String>
        get() = _category

    private val _language = MutableLiveData<String>()
    private val language: LiveData<String>
        get() = _language

    private val _navigateToSelectedProperty = MutableLiveData<ArticlesNews?>()
    val navigateToSelectedProperty: LiveData<ArticlesNews?>
        get() = _navigateToSelectedProperty

    val flow: LiveData<PagingData<ArticlesNews>> = Pager(
        PagingConfig(pageSize = 5)
    ) {

        EverythingNewsPagingSource(NewsApi.retrofitService, _category.value!!, country = "us", _language.value!!)
    }.liveData.cachedIn(viewModelScope)


    fun displayPropertyDetails(ArticlesNews: ArticlesNews) {
        _navigateToSelectedProperty.value = ArticlesNews
    }

    fun displayWebViewComplete() {
        _navigateToSelectedProperty.value = null
    }


    init {
        _language.value = "en"
        _category.value = "general"


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