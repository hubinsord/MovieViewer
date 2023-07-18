package com.example.movieviewer.ui.movie

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.domain.usecases.GetRandomMovieUseCase
import com.example.movieviewer.domain.utils.Resource
import com.example.movieviewer.domain.utils.safeCall
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
    private val getRandomMovieUseCase: GetRandomMovieUseCase
) : ViewModel() {

    private val _movie = MutableLiveData<Resource<Movie>>()
    val movie: LiveData<Resource<Movie>> get() = _movie

    @SuppressLint("CheckResult")
    private fun getRandomMovie() {
        getRandomMovieUseCase.invokeRX()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { safeCall { it } }
            .doOnSubscribe({})
            .subscribe(
                { resource ->
                    _movie.value = Resource.Loading()
                    _movie.value = resource
                },
                { t: Throwable -> }
            )
    }

    // temporarily left, used to check api calls
    private fun getMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            _movie.postValue(Resource.Loading())
            _movie.postValue(
                getRandomMovieUseCase.invoke()
            )
        }
    }

    fun onRefreshMovieClicked() {
//        getMovie()
        getRandomMovie()
    }
}