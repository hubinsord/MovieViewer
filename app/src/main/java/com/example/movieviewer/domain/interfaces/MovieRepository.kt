package com.example.movieviewer.domain.interfaces

import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.domain.utils.Resource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface MovieRepository {
    fun getRandomMovie(): Single<Movie>
    fun getMovieFromLocal(id: String): Single<Movie>
    fun getMoviesListFromLocal(isFavorite: Boolean): Single<List<Movie>>
    fun insertMovieLocal(movie: Movie): Completable
    fun updateMovie(id: String, isFavorite: Boolean): Completable
}
