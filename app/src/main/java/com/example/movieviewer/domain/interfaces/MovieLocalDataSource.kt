package com.example.movieviewer.domain.interfaces

import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.domain.utils.Resource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface MovieLocalDataSource {
    fun getMovie(): Single<Resource<Movie>>
    fun getAllMovies(): Single<Resource<List<Movie>>>
    fun insertMovie(movie: Movie): Completable
    fun deleteMovie(id: String): Completable
}