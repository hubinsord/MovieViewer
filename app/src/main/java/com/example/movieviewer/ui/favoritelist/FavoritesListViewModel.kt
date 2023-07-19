package com.example.movieviewer.ui.favoritelist

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.domain.usecases.GetMoviesListFromLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("CheckResult")
@HiltViewModel
class FavoritesListViewModel @Inject constructor(
    private val getMoviesListFromLocalUseCase: GetMoviesListFromLocalUseCase
) : ViewModel() {

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> get() = _movieList

    fun getMovies() {
        getMoviesListFromLocalUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list ->
                    Timber.tag("TEST05").d("getMoviesuseCase: $list")
                    _movieList.value = list
                },
                { t: Throwable ->
                    Timber.tag("TEST05").d(t.message)

                }
            )
    }

}