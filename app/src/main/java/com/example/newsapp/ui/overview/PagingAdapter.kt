package com.example.androidcentranewsapp.ui.overview

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.ColorFilter
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.network.news.ArticlesNews
import com.example.newsapp.databinding.GridViewItemBinding

class PagingAdapter(val onClickListener: OnClickListener) :
    PagingDataAdapter<ArticlesNews, PagingAdapter.PagingViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<ArticlesNews>() {
        override fun areItemsTheSame(oldItem: ArticlesNews, newItem: ArticlesNews): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ArticlesNews, newItem: ArticlesNews): Boolean {
            return oldItem.url == newItem.url && oldItem.content == newItem.content
        }
    }

    class PagingViewHolder(private val binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ArticlesNews: ArticlesNews?) {
            binding.article = ArticlesNews
            binding.executePendingBindings()

            binding.favoriteButton.setOnClickListener {
                binding.favoriteButton.setColorFilter(Color.BLUE)
            }
        }
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val ArticlesNewsProperty = getItem(position)
        holder.bind(ArticlesNewsProperty)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(ArticlesNewsProperty)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        return PagingViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    class OnClickListener(val clickListener: (ArticlesNews: ArticlesNews?) -> Unit) {
        fun onClick(ArticlesNews: ArticlesNews?) = clickListener(ArticlesNews)
    }
}