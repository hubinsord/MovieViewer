package com.example.movieviewer.data.entities

data class Movie(
    val id: String,
    val title: String,
    val releaseYear: String,
    val imageUrl: String,
    val isFavorite: Boolean = false
)


