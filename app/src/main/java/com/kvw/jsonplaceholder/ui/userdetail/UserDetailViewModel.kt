package com.kvw.jsonplaceholder.ui.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvw.jsonplaceholder.business.model.Album
import com.kvw.jsonplaceholder.business.model.User
import com.kvw.jsonplaceholder.business.repository.AlbumRepository
import com.kvw.jsonplaceholder.util.Intel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserDetailViewModel(user: User, albumRepository: AlbumRepository) : ViewModel() {
    private val _albums = MutableLiveData<Intel<List<Album>>>()
    val albums : LiveData<Intel<List<Album>>> get() = _albums

    init {
        viewModelScope.launch(Dispatchers.IO) {
            albumRepository.getByUser(user).collect { _albums.postValue(it) }
        }
    }
}
