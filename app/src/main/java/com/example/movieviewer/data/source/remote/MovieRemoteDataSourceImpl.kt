package com.example.movieviewer.data.source.remote

import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.data.source.remote.api.MoviesDatabaseRapidApi
import com.example.movieviewer.data.source.remote.entities.toMovieEntity
import com.example.movieviewer.domain.utils.Resource
import com.example.movieviewer.domain.interfaces.MovieRemoteDataSource
import com.example.movieviewer.domain.utils.safeCall
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val api: MoviesDatabaseRapidApi
) : MovieRemoteDataSource {


    override fun getRandomMovie(): Single<Movie> {
        return api.getRandomMovie()
            .subscribeOn(Schedulers.io())
            .map { it.toMovieEntity() }
    }
}