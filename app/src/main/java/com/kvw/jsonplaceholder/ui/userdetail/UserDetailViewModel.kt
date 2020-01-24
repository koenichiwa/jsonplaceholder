package com.kvw.jsonplaceholder.ui.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvw.jsonplaceholder.business.model.Album
import com.kvw.jsonplaceholder.business.model.User
import com.kvw.jsonplaceholder.business.repository.AlbumRepository
import com.kvw.jsonplaceholder.business.repository.util.collect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDetailViewModel(user: User, albumRepository: AlbumRepository) : ViewModel() {
    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> get() = _albums

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    init {
        viewModelScope.launch(Dispatchers.IO) {
            albumRepository.getByUser(user).collect(
                pendingLiveData = _loading,
                succesLiveData = _albums,
                errorLiveData = _errorMessage,
                userErrorMessage = "Could not load users"
            )
        }
    }
}
