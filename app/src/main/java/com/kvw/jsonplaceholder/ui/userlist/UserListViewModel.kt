package com.kvw.jsonplaceholder.ui.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvw.jsonplaceholder.business.model.User
import com.kvw.jsonplaceholder.business.repository.UserRepository
import com.kvw.jsonplaceholder.business.repository.util.collect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class UserListViewModel(userRepository: UserRepository) : ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    init {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getAll().collect(
                pendingLiveData = _loading,
                succesLiveData = _users,
                errorLiveData = _errorMessage
            )
        }
    }
}
