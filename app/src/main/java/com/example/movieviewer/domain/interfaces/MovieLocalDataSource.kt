package com.example.movieviewer.domain.interfaces

import com.example.movieviewer.data.model.Movie
import com.example.movieviewer.domain.Resource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable

interface MovieLocalDataSource {
    fun getMovie(): Single<Resource<Movie>>
    fun getAllMovies(): Single<Resource<List<Movie>>>
    fun insertMovie(movie: Movie): Completable
    fun deleteMovie(id: String): Completable
}