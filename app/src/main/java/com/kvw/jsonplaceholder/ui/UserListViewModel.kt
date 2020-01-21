package com.kvw.jsonplaceholder.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kvw.jsonplaceholder.business.model.User

class UserListViewModel : ViewModel() {
    private val _users = MutableLiveData<List<User>>(emptyList())
    val users : LiveData<List<User>> get() = _users
}
