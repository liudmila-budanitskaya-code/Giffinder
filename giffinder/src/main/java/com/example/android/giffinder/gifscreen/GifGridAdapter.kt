package com.example.android.giffinder.gifscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.giffinder.databinding.GridViewItemBinding
import com.example.android.giffinder.network.Data

class GifGridAdapter(private val clickListener: GifTapListener) : ListAdapter<Data,
        GifGridAdapter.DataViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataViewHolder {
        return DataViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data!!, clickListener)
    }

    class DataViewHolder(
        private var binding:
        GridViewItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            data: Data,
            clickListener: GifTapListener
        ) {
            binding.data = data
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }
    }
}

object DiffCallback : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.images?.original?.url == newItem.images?.original?.url
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }
}

class GifTapListener(val clickListener: (url: String) -> Unit) {
    fun onClick(data: Data) = clickListener(data.images?.original?.url.toString())
}