package com.example.movieviewer.domain.interfaces

import com.example.movieviewer.data.entities.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface MovieRepository {
    fun getRandomMovie(): Single<Movie>
    fun getMovie(id: String): Single<Movie>
    fun getMovies(isFavorite: Boolean): Single<List<Movie>>
    fun saveMovie(movie: Movie): Completable
    fun updateMovie(id: String, isFavorite: Boolean): Completable
}
