package com.example.android.giffinder

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import com.example.android.giffinder.MainApplication.Companion.applicationContext
import com.example.android.giffinder.network.Data
import com.example.android.giffinder.overview.GifGridAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {

    imgUrl?.let {

        val request = ImageRequest.Builder(applicationContext())
            .data(imgUrl)
            .target(
                onStart = {
                    imgView.setImageResource(R.drawable.loading_animation)
                },
                onSuccess = { result ->
                    imgView.load(result)
                },
                onError = { error ->
                    imgView.setImageDrawable(error)
                }
            )
            .build()
        applicationContext().imageLoader.enqueue(request)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<Data>?
) {
    val adapter = recyclerView.adapter as GifGridAdapter
    adapter.submitList(data)
}