package com.example.movieviewer.data.source.remote.entities

data class PrimaryImage(
    val __typename: String,
    val caption: Caption,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)