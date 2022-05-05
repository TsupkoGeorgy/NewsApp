package com.example.newsapp.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.newsapp.data.model.ArticlesData
import com.example.newsapp.databinding.GridViewItemBinding
import com.example.newsapp.ui.overview.NewsActionListener
import com.example.newsapp.ui.overview.PagingAdapter

class FavoritesAdapter(private val newsActionListener: NewsActionListener) :
ListAdapter<ArticlesData, PagingAdapter.PagingViewHolder>(PagingAdapter.DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PagingAdapter.PagingViewHolder {
        return PagingAdapter.PagingViewHolder(newsActionListener,
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PagingAdapter.PagingViewHolder, position: Int) {
        val articlesNewsProperty = getItem(position)
        holder.bind(articlesNewsProperty)
    }
}