package com.example.movieviewer.domain.usecases

import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.domain.interfaces.MovieRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class AddMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movie: Movie): Completable {
        return repository.insertMovieLocal(movie)
    }
}