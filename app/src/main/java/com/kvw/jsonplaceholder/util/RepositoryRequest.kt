package com.kvw.jsonplaceholder.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

class RepositoryRequest<Param>(
    private val fetch: suspend () -> Param,
    private val read: suspend () -> Param,
    private val write: suspend (Param) -> Unit
) {
    fun fromRemote(): Flow<Intel<Param>> = flow {
        emit(Intel.Pending())
        try {
            Timber.d("Started fetch from remote")
            emit(
                Intel.Success(
                    Intel.Source.Remote, fetch()))
            Timber.d("Fetched from remote")
        } catch (t: Throwable) {
            emit(
                Intel.Error<Param>(
                    Intel.Source.Remote, t, "Fetching from remote threw an exception"))
        }
    }.onEach { Timber.d("Emitting new Intel from ${it.source.name}") }

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun fromLocalFirst(): Flow<Intel<Param>> = channelFlow<Intel<Param>> {
        send(Intel.Pending())
        coroutineScope {
            val readJob = launch(Dispatchers.IO) {
                try {
                    Timber.d("Started fetch from local")
                    send(
                        Intel.Success(
                            Intel.Source.Local, read()))
                    Timber.d("Fetched from local")
                } catch (t: Throwable) {
                    send(
                        Intel.Error(
                            Intel.Source.Local, t, "Fetching from local threw an exception"))
                    Timber.e(t, "Fetching from local threw an exception")
                }
            }
            launch(Dispatchers.IO) {
                try {
                    fetch().let {
                        Timber.d("Started fetch from remote")
                        if (readJob.isActive) {
                            readJob.cancel()
                            Timber.d("Cancelled fetch from local (remote was faster)")
                        }
                        launch(Dispatchers.IO) { write(it) }
                        send(
                            Intel.Success(
                                Intel.Source.Remote, it))
                        Timber.d("Fetched from remote")
                    }
                } catch (t: Throwable) {
                    send(
                        Intel.Error(
                            Intel.Source.Remote, t, "Fetching from remote threw an exception"))
                    Timber.e(t, "Fetching from remote threw an exception")
                }
            }
        }
    }.distinctUntilChanged { old, new ->
        return@distinctUntilChanged if (old is Intel.Success<Param> && new is Intel.Success<Param>)
            old.data == new.data
        else
            false
    }.onEach { Timber.d("Emitting new Intel from ${it.source.name}") }
}
