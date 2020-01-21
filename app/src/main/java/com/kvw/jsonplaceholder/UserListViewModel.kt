package com.kvw.jsonplaceholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserListViewModel : ViewModel() {
    private val _users = MutableLiveData<List<User>>(emptyList())
    val users : LiveData<List<User>> get() = _users
}
