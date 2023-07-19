package com.example.movieviewer.domain.usecases

import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.domain.repositories.MovieRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetMoviesListFromLocalUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Single<List<Movie>> {
        return repository.getMovies(true)
    }
}