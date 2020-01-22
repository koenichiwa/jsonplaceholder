package com.kvw.jsonplaceholder.ui.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvw.jsonplaceholder.business.model.User
import com.kvw.jsonplaceholder.business.repository.UserRepository
import com.kvw.jsonplaceholder.util.Intel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class UserListViewModel(userRepository: UserRepository) : ViewModel() {
    private val _users = MutableLiveData<Intel<List<User>>>()
    val users : LiveData<Intel<List<User>>> get() = _users

    init {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getAll().collect{ _users.postValue(it) }
        }
    }
}
