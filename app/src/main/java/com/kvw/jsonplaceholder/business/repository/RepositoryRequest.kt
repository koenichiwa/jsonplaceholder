package com.kvw.jsonplaceholder.business.repository

import com.kvw.jsonplaceholder.util.Intel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryRequest<Param>(
    private val fetch: suspend () -> Param,
    private val read: suspend () -> Param,
    private val write: suspend (Param) -> Unit
){
    fun fromRemote(): Flow<Intel<Param>> = flow {
        emit(Intel.Pending())
        try {
            emit(Intel.Success(Intel.Source.Remote, fetch()))
        } catch (t: Throwable){
            emit(Intel.Error(Intel.Source.Remote, t, "Fetching from remote threw an exception"))
        }
    }

    fun fromLocalFirst(): Flow<Intel<Param>> = flow {
        emit(Intel.Pending())
        val local: Param? =
            try {
                read().also {
                    emit(Intel.Success(Intel.Source.Local, it))
                }
            } catch (t: Throwable){
                Intel.Error<Param>(Intel.Source.Local, t, "Fetching from remote threw an exception")
                null
            }


        try {
            fetch().takeUnless{ it == local }?.let {
                write(it)
                emit(Intel.Success(Intel.Source.Remote, it))
            }
        } catch (t: Throwable){
            emit(Intel.Error(Intel.Source.Remote, t, "Fetching from remote threw an exception"))
        }
    }
}