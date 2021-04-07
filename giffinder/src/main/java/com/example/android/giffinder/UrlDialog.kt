package com.example.android.giffinder

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.android.giffinder.databinding.UrlDialogBinding

private const val ARG_PARAM1 = "param1"

class UrlDialog : DialogFragment() {
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = UrlDialogBinding.inflate(inflater)

        binding.textView.text = param1

        binding.shareIcon.setOnClickListener {
            startActivity(Intent.createChooser(Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, param1)
                type = "text/plain"
            }, null))
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String) =
            UrlDialog().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}