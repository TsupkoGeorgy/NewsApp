package com.example.newsapp.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.model.ArticlesData
import com.example.newsapp.databinding.GridViewItemBinding

class PagingAdapter(private val newsActionListener: NewsActionListener) :
    PagingDataAdapter<ArticlesData, PagingAdapter.PagingViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<ArticlesData>() {
        override fun areItemsTheSame(oldItem: ArticlesData, newItem: ArticlesData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ArticlesData, newItem: ArticlesData): Boolean {
            return oldItem.url == newItem.url && oldItem.content == newItem.content
        }
    }

    class PagingViewHolder(private val newsActionListener: NewsActionListener, private val binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(articlesData: ArticlesData?) {
            binding.article = articlesData
            binding.clickListener = newsActionListener
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val articlesNewsProperty = getItem(position)
        holder.bind(articlesNewsProperty)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        return PagingViewHolder(newsActionListener,GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }
}

interface NewsActionListener {

    fun onNewsClicked(articlesNews: ArticlesData)

    fun onFavoriteClicked(articlesNews: ArticlesData)
}