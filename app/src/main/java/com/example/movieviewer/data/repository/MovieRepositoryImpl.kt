package com.example.movieviewer.data.repository

import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.domain.interfaces.MovieLocalDataSource
import com.example.movieviewer.domain.interfaces.MovieRemoteDataSource
import com.example.movieviewer.domain.interfaces.MovieRepository
import com.example.movieviewer.domain.utils.Resource
import com.example.movieviewer.domain.utils.safeCall
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

    // temporarily left, used to check api calls
    override suspend fun getMovie(): Resource<Movie> {
        return safeCall {
            remoteDataSource.getMovie()
        }
    }

    override fun getMovieFromLocal(id: String): Single<Movie> {
        return localDataSource.getMovie(id)
    }

    override fun getMoviesListFromLocal(isFavorite: Boolean): Single<List<Movie>> {
        return localDataSource.getAllMovies(isFavorite)
    }

    override fun insertMovieLocal(movie: Movie): Completable {
        return localDataSource.insertMovie(movie)
    }
}