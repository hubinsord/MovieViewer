package com.example.movieviewer.data.source.remote

import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.data.source.remote.api.MoviesDatabaseRapidApi
import com.example.movieviewer.data.source.remote.entities.toMovieEntity
import com.example.movieviewer.data.source.MovieRemoteDataSource
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val api: MoviesDatabaseRapidApi
) : MovieRemoteDataSource {
    override fun getRandomMovie(): Single<Movie> = api.getRandomMovie().map { it.toMovieEntity() }
}