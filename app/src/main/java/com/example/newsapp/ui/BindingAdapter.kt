package com.example.newsapp.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

//@BindingAdapter("listData")
//fun bindRecyclerView(recyclerView: RecyclerView, data: List<Articles>?) {
//    val adapter = recyclerView.adapter as NewsAdapter
//    adapter.submitList(data)
//}

//@BindingAdapter("listPagingData")
//suspend fun bindPagingRecyclerViewPaging(
//    recyclerView: RecyclerView,
//    data: PagingData<Articles>
//) {
//    val adapter = recyclerView.adapter as PagingAdapter
//
//        adapter.submitData(data)
//}

@BindingAdapter("dataFormat")
fun TextView.dataFormat(item: String?) {
    item?.let {
        val data: Date = SimpleDateFormat("yyyy-MM-dd").parse(item)
        val weekdayString = SimpleDateFormat("EEE, d MMM yyyy", Locale.ENGLISH).format(data)
        text =  weekdayString.toString()
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUrl = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUrl)
            .into(imgView)
    }
}