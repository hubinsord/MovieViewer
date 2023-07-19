package com.example.movieviewer.data.source.local.di

import android.content.Context
import androidx.room.Room
import com.example.movieviewer.data.source.local.MovieLocalDataSourceImpl
import com.example.movieviewer.data.source.local.dao.MovieDao
import com.example.movieviewer.data.source.local.db.MovieDatabase
import com.example.movieviewer.data.source.MovieLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MovieDatabase::class.java, MovieDao.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideMovieDao(database: MovieDatabase) =
        database.getMovieDao()

    @Singleton
    @Provides
    fun provideLocalDataSource(database: MovieDatabase): MovieLocalDataSource = MovieLocalDataSourceImpl(database)
}