package com.example.movieviewer.domain.usecases

import com.example.movieviewer.domain.interfaces.MovieRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class UpdateMoveUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(id: String, isFavorite: Boolean) : Completable {
        return repository.updateMovie(id, isFavorite)
    }
}