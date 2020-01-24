package com.kvw.jsonplaceholder.ui.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kvw.jsonplaceholder.business.model.User
import com.kvw.jsonplaceholder.databinding.ItemUserUserlistBinding

class UserListAdapter(
    private val users: List<User>,
    private val onClick: (User) -> Unit
) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = LayoutInflater.from(parent.context)
            .let { ItemUserUserlistBinding.inflate(it) }
        return ViewHolder(viewBinding)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
        holder.itemViewBinding.root.setOnClickListener { onClick(users[position]) }
    }

    inner class ViewHolder(val itemViewBinding: ItemUserUserlistBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        fun bind(user: User) {
            // Bind ViewHolder here
            itemViewBinding.user = user
            itemViewBinding.executePendingBindings()
        }
    }
}
