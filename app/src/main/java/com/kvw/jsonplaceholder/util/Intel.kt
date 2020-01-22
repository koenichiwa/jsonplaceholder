package com.kvw.jsonplaceholder.util

sealed class Intel<T>(val source: Source) {
    class Pending<T> : Intel<T>(Source.Local)
    class Success<T>(source: Source, val data: T) : Intel<T>(source)
    class Error<T>(source: Source, val throwable: Throwable, val reason: String): Intel<T>(source)
    enum class Source{
        Local,
        Remote
    }
}

