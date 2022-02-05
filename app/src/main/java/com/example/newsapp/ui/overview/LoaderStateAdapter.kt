package com.example.androidcentranewsapp.ui.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemLoaderStateBinding

class LoaderStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>() {

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        return LoaderViewHolder(
            ItemLoaderStateBinding.inflate(LayoutInflater.from(parent.context)),
            retry
        )
    }


    class LoaderViewHolder(private val binding: ItemLoaderStateBinding, retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnRetry.setOnClickListener {
                retry()
            }

        }

        fun bind(loadState: LoadState) {

            if (loadState is LoadState.Error) {
                binding.tvError.text = loadState.error.localizedMessage
            }
            binding.pbLoader.visibility = toVisibility(loadState is LoadState.Loading)
            binding.btnRetry.visibility = toVisibility(loadState !is LoadState.Loading)
            binding.tvError.visibility = toVisibility(loadState !is LoadState.Loading)

//            when (loadState) {
//                LoadState.Loading -> {
//                    binding.btnRetry.isVisible = false
//                    binding.tvError.isVisible = false
//                }
//                is LoadState.Error -> {
//                    binding.btnRetry.isVisible = true
//                    binding.tvError.isVisible = true
//                    binding.pbLoader.isVisible = false
//                    binding.tvError.text = loadState.error.message
//                }
//            }

        }

        private fun toVisibility(constraint: Boolean): Int = if (constraint) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}