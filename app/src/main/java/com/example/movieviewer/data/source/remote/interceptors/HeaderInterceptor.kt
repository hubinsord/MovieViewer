package com.example.movieviewer.data.source.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.run {
            proceed(
                request()
                    .newBuilder()
                    .addHeader("X-RapidAPI-Key", "9be336a500msh64585eb4e50c2d0p125594jsn16210933b82b")
                    .addHeader("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com")
//                    .removeHeader("User-Agent")
//                    .addHeader("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:38.0) Gecko/20100101 Firefox/38.0")
                    .build()
            )
        }
}