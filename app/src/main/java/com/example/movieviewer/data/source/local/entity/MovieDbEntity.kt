package com.example.movieviewer.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.data.source.local.dao.MovieDao

@Entity(tableName = MovieDao.DB_NAME)
data class MovieDbEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val releaseYear: String,
    val imageUrl: String,
    val isFavorite: Boolean
)

fun MovieDbEntity.toMovie(): Movie =
    Movie(
        id = id,
        title = title,
        releaseYear = releaseYear,
        imageUrl = imageUrl,
        isFavorite = isFavorite
    )

fun Movie.toMovieDbEntity() : MovieDbEntity =
    MovieDbEntity(
        id = id,
        title = title,
        releaseYear = releaseYear,
        imageUrl = imageUrl,
        isFavorite = isFavorite
    )