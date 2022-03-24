package com.assesment.truecaller.webcontentprocessor.core.util

sealed class Result<T> {
    data class Initial<T>(val data: String = "Loading...") : Result<T>()
    data class Success<T>(val data: T) : Result<T>()
    data class Failure<T>(val error: String?) : Result<T>()
}