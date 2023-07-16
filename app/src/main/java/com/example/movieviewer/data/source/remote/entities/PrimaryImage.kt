package com.example.movieviewer.data.source.remote.entities

import com.squareup.moshi.Json

data class PrimaryImage(
//    @Json(name = "__typename")
//    val __typename: String,

//    @Json(name = "caption")
//    val caption: Caption,

    @Json(name = "height")
    val height: Int,

    @Json(name = "id")
    val id: String,

    @Json(name = "url")
    val url: String,

    @Json(name = "width")
    val width: Int
)
