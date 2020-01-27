package com.kvw.jsonplaceholder.ui.albumdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.kvw.jsonplaceholder.R
import com.kvw.jsonplaceholder.business.model.Photo
import com.kvw.jsonplaceholder.databinding.ItemPhotoAlbumdetailBinding
import com.squareup.picasso.Picasso

class AlbumDetailPhotoListAdapter(
    private val photos: List<Photo>,
    private val onClick: (Photo) -> Unit
) : RecyclerView.Adapter<AlbumDetailPhotoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = LayoutInflater.from(parent.context)
            .let { ItemPhotoAlbumdetailBinding.inflate(it) }
        return ViewHolder(viewBinding)
    }

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photos[position])
        holder.itemViewBinding.root.setOnClickListener { onClick(photos[position]) }
    }

    inner class ViewHolder(val itemViewBinding: ItemPhotoAlbumdetailBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        fun bind(photo: Photo) {
            itemViewBinding.photo = photo
            itemViewBinding.executePendingBindings()
            itemViewBinding.root.findViewById<ImageView>(R.id.imageView_itemPhoto_albumDetail)
                .let {
                    Picasso.get()
                        .load(photo.url)
                        .into(it)
                }
        }
    }
}
