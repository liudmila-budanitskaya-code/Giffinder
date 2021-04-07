/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.giffinder.overview

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
//        val binding = GridViewItemBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        binding.photosGrid.adapter = GifGridAdapter(GifTapListener { url ->
            val dialog = UrlDialog.newInstance(url)

            val fm = requireActivity().supportFragmentManager
            dialog.show(fm, "photo_dialog")
        })

        binding.searchButton.setOnClickListener{
            viewModel.getSearchedGifs(binding.searchView.text.toString())
        }

        return binding.root
    }
}
