package com.example.movieviewer.data.entities

import com.example.movieviewer.R

data class Movie(
    val id: String,
    val title: String,
    val releaseYear: String,
    val imageUrl: String,
    val isFavorite: Boolean = false
) {
    val favoriteDrawable
        get() = if (isFavorite) R.drawable.iv_favorite_filled else R.drawable.iv_favorite_outline
}

