package com.kvw.jsonplaceholder.util

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import timber.log.Timber

sealed class Intel<T>(val source: Source) {
    class Pending<T> : Intel<T>(
        Source.Local
    )
    class Success<T>(source: Source, val data: T) : Intel<T>(source)
    class Error<T>(source: Source, val throwable: Throwable, val reason: String) : Intel<T>(source)
    enum class Source {
        Local,
        Remote
    }
}

suspend inline fun <T> Flow<Intel<out T>>.collect(
    crossinline onPending: () -> Unit = {},
    crossinline onSuccess: (T) -> Unit,
    crossinline onError: (Intel.Error<out T>) -> Unit
) {
    this.collect {
        when (it) {
            is Intel.Pending -> {
                onPending()
            }
            is Intel.Success -> {
                onSuccess(it.data)
            }
            is Intel.Error -> {
                Timber.e(it.throwable, it.reason)
                onError(it)
            }
        }
    }
}

suspend inline fun <T> Flow<Intel<out T>>.collect(
    pendingLiveData: MutableLiveData<Boolean>,
    succesLiveData: MutableLiveData<T>,
    errorLiveData: MutableLiveData<String>
) {
    this.collect(
        onPending = { pendingLiveData.postValue(true) },
        onSuccess = {
            pendingLiveData.postValue(false)
            succesLiveData.postValue(it)
        },
        onError = {
            pendingLiveData.postValue(false)
            errorLiveData.postValue(it.reason)
        }
    )
}

suspend inline fun <T> Flow<Intel<out T>>.collect(
    pendingLiveData: MutableLiveData<Boolean>,
    succesLiveData: MutableLiveData<T>,
    errorLiveData: MutableLiveData<String>,
    userErrorMessage: String
) {
    this.collect(
        onPending = { pendingLiveData.postValue(true) },
        onSuccess = {
            pendingLiveData.postValue(false)
            succesLiveData.postValue(it)
        },
        onError = {
            pendingLiveData.postValue(false)
            errorLiveData.postValue(userErrorMessage)
        }
    )
}
