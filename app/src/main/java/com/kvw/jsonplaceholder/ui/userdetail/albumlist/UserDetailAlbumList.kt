package com.kvw.jsonplaceholder.ui.userdetail.albumlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kvw.jsonplaceholder.R
import com.kvw.jsonplaceholder.ui.userdetail.UserDetailViewModel
import kotlinx.android.synthetic.main.fragment_userdetail_albumlist.*

class UserDetailAlbumList(private val userListViewModel: UserDetailViewModel) : Fragment() {

    companion object {
        fun newInstance(userListViewModel: UserDetailViewModel) =
            UserDetailAlbumList(
                userListViewModel
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_userdetail_albumlist, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        userListViewModel.albums.observe(this.viewLifecycleOwner, Observer { albums ->
                recyclerView_userDetail_albumList.swapAdapter(
                    UserDetailAlbumListAdapter(albums) {}, true)
            }
        )
    }
}
