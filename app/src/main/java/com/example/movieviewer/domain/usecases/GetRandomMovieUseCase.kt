package com.example.movieviewer.domain.usecases

import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.domain.utils.Resource
import com.example.movieviewer.domain.interfaces.MovieRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetRandomMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): Resource<Movie> {
        return movieRepository.getMovie()
    }

     fun invokeRX() : Single<Movie>{
          return movieRepository.getRandomMovie()
     }
}