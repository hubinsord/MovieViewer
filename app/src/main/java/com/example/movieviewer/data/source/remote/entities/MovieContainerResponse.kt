package com.example.movieviewer.data.source.remote.entities

import com.squareup.moshi.Json

data class MovieContainerResponse(
    @Json(name="entries")
    val entries: Int,
    @Json(name="results")
    val results: List<Result>
)