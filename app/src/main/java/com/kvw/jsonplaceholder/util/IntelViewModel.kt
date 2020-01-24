package com.kvw.jsonplaceholder.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class IntelViewModel : ViewModel() {
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun <T> loadData(successLiveData: MutableLiveData<T>, loadFunction: () -> Flow<Intel<out T>>) {
        viewModelScope.launch(Dispatchers.IO) {
            loadFunction().collect(
                pendingLiveData = _loading,
                succesLiveData = successLiveData,
                errorLiveData = _errorMessage,
                userErrorMessage = "Could not load albums"
            )
        }
    }
}
