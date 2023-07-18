package com.example.movieviewer.domain.interfaces

import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.domain.utils.Resource
import io.reactivex.rxjava3.core.Single

interface MovieRepository {
    fun getRandomMovie(): Single<Movie>
    suspend fun getMovie(): Resource<Movie>
}
