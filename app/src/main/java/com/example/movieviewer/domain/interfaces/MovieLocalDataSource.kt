package com.example.movieviewer.domain.interfaces

import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.domain.utils.Resource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface MovieLocalDataSource {
    fun getMovie(id: String): Single<Movie>
    fun getAllMovies(isFavorite: Boolean): Single<List<Movie>>
    fun insertMovie(movie: Movie): Completable
    fun deleteMovie(id: String): Completable
}