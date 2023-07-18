package com.example.movieviewer.data.source.remote.entities

import com.squareup.moshi.Json

data class TitleText(
    @Json(name = "__typename")
    val __typename: String?,

    @Json(name = "text")
    val text: String?
)
