package com.example.android.giffinder.network

import com.squareup.moshi.Json

data class Gif(
    val id: String,
    @Json(name = "img_src") val imgSrcUrl: String
)

data class Original(val url: String?)

data class NestedJSONModel(
    var data: List<Data>?
)

data class Data(
    val id: String,
    val images: Images?
)


data class Images(
    val original: Original
)