package com.example.movieviewer.data.source.remote.entities

data class Result(
    val _id: String,
    val id: String,
    val originalTitleText: OriginalTitleText,
    val position: Int,
    val primaryImage: PrimaryImage,
    val releaseDate: ReleaseDate,
    val releaseYear: ReleaseYear,
    val titleText: TitleText,
    val titleType: TitleType
)