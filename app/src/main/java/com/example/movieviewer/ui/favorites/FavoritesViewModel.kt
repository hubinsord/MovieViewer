package com.example.movieviewer.ui.favorites

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.domain.usecases.GetMoviesListFromLocalUseCase
import com.example.movieviewer.domain.usecases.UpdateMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getMoviesListFromLocalUseCase: GetMoviesListFromLocalUseCase,
    private val updateMoveUseCase: UpdateMovieUseCase,
) : ViewModel() {

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> get() = _movieList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    @SuppressLint("CheckResult")
    fun getMovies() {
        getMoviesListFromLocalUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list -> _movieList.value = list },
                { t: Throwable -> _error.value = t.message }
            )
    }

    fun onFavoriteClicked(id: String, isFavorite: Boolean) {
        updateMovie(id, isFavorite)
        getMovies()
    }

    @SuppressLint("CheckResult")
    private fun updateMovie(id: String, isFavorite: Boolean) {
        updateMoveUseCase.invoke(id, !isFavorite)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { },
                { t: Throwable -> _error.value = t.message })
    }
}