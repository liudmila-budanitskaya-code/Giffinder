package com.example.android.giffinder.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.giffinder.network.Data
import com.example.android.giffinder.network.GifApi
import kotlinx.coroutines.launch
import java.lang.Exception


class GifGridViewModel : ViewModel() {

    private val _gifs = MutableLiveData<List<Data>>()
    val gifs: LiveData<List<Data>> = _gifs

    init {
        getTrendingGifsImmediately()
    }

    private fun getTrendingGifsImmediately() {
        viewModelScope.launch {
            try {
                _gifs.value = GifApi.retrofitService.getTrendingGifs().body()?.data!!
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getSearchedGifs(query: String) {
        viewModelScope.launch {
            try {
                _gifs.value = GifApi.retrofitService.getQueredGifs(query).body()?.data!!
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
