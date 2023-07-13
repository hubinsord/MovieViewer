package com.example.movieviewer.domain.interfaces

import com.example.movieviewer.data.model.Movie
import com.example.movieviewer.domain.Resource
import io.reactivex.rxjava3.core.Single

interface MovieRemoteDataSource {
    fun getRandomMovie(): Single<Resource<Movie>>
}