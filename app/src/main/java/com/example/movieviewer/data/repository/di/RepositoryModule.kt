package com.example.movieviewer.data.repository.di

import com.example.movieviewer.data.repository.MovieRepositoryImpl
import com.example.movieviewer.data.source.MovieLocalDataSource
import com.example.movieviewer.data.source.MovieRemoteDataSource
import com.example.movieviewer.domain.repositories.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        remoteDataSource: MovieRemoteDataSource,
        localDataSource: MovieLocalDataSource
        ): MovieRepository =
        MovieRepositoryImpl(remoteDataSource, localDataSource)
}