package com.kvw.jsonplaceholder.util

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import timber.log.Timber

sealed class Intel<T>(val source: Source) {
    class Pending<T> : Intel<T>(Source.Local)
    class Success<T>(source: Source, val data: T) : Intel<T>(source)
    class Error<T>(source: Source, val throwable: Throwable, val reason: String) : Intel<T>(source)
    enum class Source {
        Local,
        Remote
    }
}

fun <T> LiveData<Intel<T>>.observe(
    owner: LifecycleOwner,
    onPending: () -> Unit = {},
    onSuccess: (T) -> Unit,
    onError: (Throwable, String) -> Unit
) {
    this.observe(owner, Observer {
        when (it) {
            is Intel.Pending -> {
                onPending()
                Timber.d("Waiting for intel")
            }
            is Intel.Success -> {
                Timber.d("Intel received")
                onSuccess(it.data)
            }
            is Intel.Error -> {
                Timber.d(it.throwable, "Intel collection of threw an error, reason: ${it.reason}")
                onError(it.throwable, it.reason)
            }
        }
    })
}

fun <T> LiveData<Intel<T>>.observe(
    owner: LifecycleOwner,
    pendingView: View?,
    onSuccess: (T) -> Unit,
    onError: (Throwable, String) -> Unit
) {
    this.observe(owner,
        onPending = {
            pendingView?.visibility = View.VISIBLE
        },
        onSuccess = {
            pendingView?.visibility = View.GONE
            onSuccess(it)
        },
        onError = onError
    )
}
