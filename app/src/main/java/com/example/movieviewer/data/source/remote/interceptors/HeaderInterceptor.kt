package com.example.movieviewer.data.source.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.run {
            proceed(
                request()
                    .newBuilder()
                    //normally I would place api key in local.properties to avoid placing it on github, but for the sake of
                    //the task I left it here to make it possible to build the app
                    .addHeader("X-RapidAPI-Key", "9be336a500msh64585eb4e50c2d0p125594jsn16210933b82b")
                    .addHeader("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com")
                    .build()
            )
        }
}