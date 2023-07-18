package com.example.movieviewer.domain.di

import com.example.movieviewer.domain.interfaces.MovieRepository
import com.example.movieviewer.domain.usecases.GetRandomMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGetRandomMovieUseCase(repository: MovieRepository) = GetRandomMovieUseCase(repository)
}