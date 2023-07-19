package com.example.movieviewer.ui.favoritelist

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.PrimaryKey
import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.domain.usecases.GetMoviesListFromLocalUseCase
import com.example.movieviewer.domain.usecases.UpdateMoveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavoritesListViewModel @Inject constructor(
    private val getMoviesListFromLocalUseCase: GetMoviesListFromLocalUseCase,
    private val updateMoveUseCase: UpdateMoveUseCase,
) : ViewModel() {

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> get() = _movieList

    @SuppressLint("CheckResult")
    fun getMovies() {
        getMoviesListFromLocalUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list -> _movieList.value = list },
                { t: Throwable ->  }
            )
    }

    fun onFavoriteClicked(id: String, isFavorite: Boolean) {
        updateMovie(id, isFavorite)
    }

    private fun updateMovie(id: String, isFavorite: Boolean) {
        updateMoveUseCase.invoke(id, !isFavorite)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}