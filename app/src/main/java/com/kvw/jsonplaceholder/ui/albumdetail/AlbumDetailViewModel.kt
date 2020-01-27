package com.kvw.jsonplaceholder.ui.albumdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kvw.jsonplaceholder.business.model.Album
import com.kvw.jsonplaceholder.business.model.Photo
import com.kvw.jsonplaceholder.business.repository.PhotoRepository
import com.kvw.jsonplaceholder.util.IntelViewModel

class AlbumDetailViewModel(album: Album, photoRepository: PhotoRepository) : IntelViewModel() {
    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> get() = _photos

    init {
        loadData(_photos) { photoRepository.getByAlbum(album) }
    }
}
