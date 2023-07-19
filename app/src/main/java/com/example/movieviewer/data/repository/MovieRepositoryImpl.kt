package com.example.movieviewer.data.repository

import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.data.source.MovieLocalDataSource
import com.example.movieviewer.data.source.MovieRemoteDataSource
import com.example.movieviewer.domain.repositories.MovieRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) : MovieRepository {

    override fun getRandomMovie(): Single<Movie> {
        return remoteDataSource.getRandomMovie()
    }

    override fun getMovie(id: String): Single<Movie> {
        return localDataSource.getMovie(id)
    }

    override fun getMovies(isFavorite: Boolean): Single<List<Movie>> {
        return localDataSource.getAllMovies(isFavorite)
    }

    override fun saveMovie(movie: Movie): Completable {
        return localDataSource.insertMovie(movie)
    }

    override fun updateMovie(id: String, isFavorite: Boolean) : Completable {
        return localDataSource.updateMovie(id, isFavorite)
    }
}