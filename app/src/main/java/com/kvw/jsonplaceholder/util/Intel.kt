package com.kvw.jsonplaceholder.util

sealed class Intel<T> {
    class Pending<T> : Intel<T>()
    class Success<T>(val data: T) : Intel<T>()
    class Error<T>(val throwable: Throwable, val reason: String): Intel<T>()
}