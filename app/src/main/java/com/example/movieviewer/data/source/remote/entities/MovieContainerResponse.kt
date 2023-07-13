package com.example.movieviewer.data.source.remote.entities

data class MovieContainerResponse(
    val entries: Int,
    val results: List<Result>
)