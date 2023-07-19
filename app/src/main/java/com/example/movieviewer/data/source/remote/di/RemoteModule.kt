package com.example.movieviewer.data.source.remote.di

import com.example.movieviewer.data.source.remote.MovieRemoteDataSourceImpl
import com.example.movieviewer.data.source.remote.api.MoviesDatabaseRapidApi
import com.example.movieviewer.data.source.remote.interceptors.HeaderInterceptor
import com.example.movieviewer.data.source.MovieRemoteDataSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideMoviesDatabaseRapidApi(retrofit: Retrofit): MoviesDatabaseRapidApi =
        retrofit.create(MoviesDatabaseRapidApi::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(moshiConverterFactory: MoshiConverterFactory, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(MoviesDatabaseRapidApi.BASE_URL)
            .addConverterFactory(moshiConverterFactory.asLenient())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()

    @Singleton
    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create(
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    )

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(HeaderInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(api: MoviesDatabaseRapidApi) : MovieRemoteDataSource = MovieRemoteDataSourceImpl(api)
}