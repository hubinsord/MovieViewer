package com.example.movieviewer.data.source.remote.entities

import com.example.movieviewer.data.entities.Movie
import com.squareup.moshi.Json

data class MovieContainerResponse(
    @Json(name="entries")
    val entries: Int,
    @Json(name="results")
    val results: List<Result>
)

fun MovieContainerResponse.toMovieEntity() = Movie(
    id = results.first()._id,
    title = results.first().titleText.text ?: "",
    releaseYear = results.first().releaseYear.year.toString() ?: "",
    imageUrl = results.first().primaryImage.url ?: ""
)