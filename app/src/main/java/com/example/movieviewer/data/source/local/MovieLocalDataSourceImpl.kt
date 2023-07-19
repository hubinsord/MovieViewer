package com.example.movieviewer.data.source.local

import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.data.source.local.db.MovieDatabase
import com.example.movieviewer.data.source.local.entity.toMovie
import com.example.movieviewer.data.source.local.entity.toMovieDbEntity
import com.example.movieviewer.domain.interfaces.MovieLocalDataSource
import com.example.movieviewer.domain.utils.Resource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val movieDatabase: MovieDatabase
) : MovieLocalDataSource {
    private val movieDao = movieDatabase.getMovieDao()

    override fun getMovie(id: String): Single<Movie> {
        return movieDao.getMovie(id)
            .map { it.toMovie() }
    }

    override fun getAllMovies(isFavorite: Boolean): Single<List<Movie>> {
    return movieDao.getAllMovies(isFavorite)
        .concatMap { list ->
            Observable.fromIterable(list)
                .map{ it.toMovie()}
                .toList()
        }
    }


    override fun insertMovie(movie: Movie): Completable {
        return Completable.fromAction { movieDao.insertMovie(movie = movie.toMovieDbEntity()) }
    }

    override fun deleteMovie(id: String): Completable {
        return Completable.fromAction { movieDao.deleteMovie() }
    }
}