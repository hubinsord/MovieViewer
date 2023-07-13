package com.example.movieviewer.domain

import com.example.movieviewer.data.model.Movie
import com.example.movieviewer.data.repository.MovieRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetRandomMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Single<Resource<Movie>> {
        return movieRepository.getRandomMovie()
    }
}