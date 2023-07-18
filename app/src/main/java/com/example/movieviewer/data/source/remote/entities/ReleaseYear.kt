package com.example.movieviewer.data.source.remote.entities

import com.squareup.moshi.Json

data class ReleaseYear(
    @Json(name = "__typename")
    val __typename: String,

    @Json(name = "endYear")
    val endYear: String? ,

    @Json(name = "year")
    val year: String?
)
