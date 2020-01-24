package com.kvw.jsonplaceholder.ui.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kvw.jsonplaceholder.business.model.Album
import com.kvw.jsonplaceholder.business.model.User
import com.kvw.jsonplaceholder.business.repository.AlbumRepository
import com.kvw.jsonplaceholder.util.IntelViewModel

class UserDetailViewModel(user: User, albumRepository: AlbumRepository) : IntelViewModel() {
    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> get() = _albums

    init { loadData(_albums) { albumRepository.getByUser(user) } }
}
