package com.example.android.giffinder.gifscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.giffinder.UrlDialog
import com.example.android.giffinder.databinding.FragmentGifGridBinding

class GifGridFragment : Fragment() {

    private val viewModel: GifGridViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGifGridBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.photosGrid.adapter = GifGridAdapter(GifTapListener { url ->
            val dialog = UrlDialog.newInstance(url)
            val fm = requireActivity().supportFragmentManager
            dialog.show(fm, "url_dialog")
        })

        binding.searchButton.setOnClickListener {
            viewModel.getSearchedGifs(binding.searchView.text.toString())
        }

        return binding.root
    }
}
