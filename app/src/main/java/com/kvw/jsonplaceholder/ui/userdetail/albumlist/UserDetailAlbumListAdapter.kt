package com.kvw.jsonplaceholder.ui.userdetail.albumlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kvw.jsonplaceholder.business.model.Album
import com.kvw.jsonplaceholder.databinding.ItemAlbumAlbumlistBinding

class UserDetailAlbumListAdapter(
    private val albums: List<Album>,
    private val onClick: (Album) -> Unit
): RecyclerView.Adapter<UserDetailAlbumListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = LayoutInflater.from(parent.context)
            .let { ItemAlbumAlbumlistBinding.inflate(it) }
        return ViewHolder(viewBinding)
    }

    override fun getItemCount() = albums.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(albums[position])
        holder.itemViewBinding.root.setOnClickListener{ onClick(albums[position]) }
    }

    inner class ViewHolder(val itemViewBinding: ItemAlbumAlbumlistBinding)
        : RecyclerView.ViewHolder(itemViewBinding.root){
        fun bind(album: Album){
            // Bind ViewHolder here
            itemViewBinding.album = album
            itemViewBinding.executePendingBindings()
        }
    }
}