package com.kvw.jsonplaceholder.ui.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kvw.jsonplaceholder.business.model.User
import com.kvw.jsonplaceholder.business.repository.UserRepository
import com.kvw.jsonplaceholder.util.IntelViewModel

class UserListViewModel(userRepository: UserRepository) : IntelViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    init { loadData(_users, userRepository::getAll) }
}
