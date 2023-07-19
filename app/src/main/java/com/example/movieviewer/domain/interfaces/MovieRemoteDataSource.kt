package com.example.movieviewer.domain.interfaces

import com.example.movieviewer.data.entities.Movie
import io.reactivex.rxjava3.core.Single

interface MovieRemoteDataSource {
    fun getRandomMovie(): Single<Movie>
}