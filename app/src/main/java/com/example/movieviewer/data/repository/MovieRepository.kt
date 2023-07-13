package com.example.movieviewer.data.repository

import com.example.movieviewer.data.model.Movie
import com.example.movieviewer.domain.Resource
import io.reactivex.rxjava3.core.Single

interface MovieRepository {
    fun getRandomMovie(): Single<Resource<Movie>>
}
