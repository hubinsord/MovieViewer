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

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getRandomMovieUseCase: GetRandomMovieUseCase,
    private val addMovieUseCase: AddMovieUseCase,
) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

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
                { t: Throwable ->
                    _isLoading.value = false
                    _error.value = t.message
                }
            )
    }

    fun onRefreshMovieClicked() {
        getRandomMovie()
    }

    @SuppressLint("CheckResult")
    fun onIsFavoriteClicked() {
        viewModelScope.launch(Dispatchers.Main) {
            _movie.value?.let {
                val movieCopy = it.copy(isFavorite = !it.isFavorite)
                _movie.value = movieCopy
            }
            movie.value?.let {
                addMovieUseCase.invoke(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {},
                        { t: Throwable -> _error.value = t.message }
                    )
            }
        }
    }
}