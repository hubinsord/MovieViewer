package com.example.movieviewer.data.source.remote.entities

import com.squareup.moshi.Json

data class Result(

    @Json(name = "_id")
    val _id: String,

    @Json(name = "id")
    val id: String,

    @Json(name = "primaryImage")
    val primaryImage: PrimaryImage,

    @Json(name = "releaseYear")
    val releaseYear: ReleaseYear,

    @Json(name = "titleText")
    val titleText: TitleText,

//    @Json(name = "originalTitleText")
//    val originalTitleText: OriginalTitleText,

//    @Json(name = "position")
//    val position: Int,

//    @Json(name = "releaseDate")
//    val releaseDate: ReleaseDate,

//    @Json(name = "titleType")
//    val titleType: TitleType
)
