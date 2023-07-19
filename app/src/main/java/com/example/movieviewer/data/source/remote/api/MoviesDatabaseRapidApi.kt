package com.example.movieviewer.data.source.remote.api

import com.example.movieviewer.data.source.remote.entities.MovieContainerResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesDatabaseRapidApi {

    @GET("titles/random")
    fun getRandomMovie(
        @Query("limit") limit: Int = 1,
        @Query("list") list: String = "most_pop_movies"
    ): Single<MovieContainerResponse>

    companion object {
        const val BASE_URL = "https://moviesdatabase.p.rapidapi.com/"
    }
}