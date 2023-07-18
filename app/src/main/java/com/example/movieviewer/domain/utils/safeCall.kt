package com.example.movieviewer.domain.utils

inline fun <T> safeCall(block: () -> T): Resource<T> =
    try {
        Resource.Success(block.invoke())
    } catch (exception: Throwable) {
        Resource.Error(exception.message)
    }