package com.example.movieviewer.ui.movie

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.domain.usecases.AddMovieUseCase
import com.example.movieviewer.domain.usecases.GetRandomMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MovieUiState(
    val movie: Movie,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)


@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getRandomMovieUseCase: GetRandomMovieUseCase,
    private val addMovieUseCase: AddMovieUseCase,
) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isFavorite: LiveData<Boolean> get() = _isLoading

    @SuppressLint("CheckResult")
    private fun getRandomMovie() {
        getRandomMovieUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _isLoading.value = true }
            .subscribe(
                { randomMovie ->
                    _isLoading.value = false
                    _movie.value = randomMovie
                },
                { t: Throwable -> }
            )
    }


    fun onRefreshMovieClicked() {
        getRandomMovie()
    }

    fun onIsFavoriteClicked() {
        viewModelScope.launch(Dispatchers.Main) {
            _movie.value?.let { movie ->
                val movieCopy = movie.copy(isFavorite = !movie.isFavorite)
                _movie.value = movieCopy
            }
            addMovieUseCase.invoke(movie.value!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
    }
}