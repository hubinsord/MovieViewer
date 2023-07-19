package com.example.movieviewer.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.movieviewer.data.entities.Movie
import com.example.movieviewer.data.source.local.entity.MovieDbEntity
import com.example.movieviewer.domain.utils.Resource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface MovieDao {
    @Query("SELECT * FROM $DB_NAME WHERE id = :id")
    fun getMovie(id: String): Single<MovieDbEntity>

    @Query("SELECT * FROM $DB_NAME WHERE isFavorite = :isFavorite")
    fun getAllMovies(isFavorite: Boolean): Single<List<MovieDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieDbEntity)

    @Query("UPDATE $DB_NAME SET isFavorite = :isFavorite WHERE id =:id")
    fun updateMovie(id: String, isFavorite: Boolean)

    @Query("DELETE FROM $DB_NAME")
    fun deleteMovie() : Completable

    companion object {
        const val DB_NAME = "MOVIE_TABLE"
    }
}