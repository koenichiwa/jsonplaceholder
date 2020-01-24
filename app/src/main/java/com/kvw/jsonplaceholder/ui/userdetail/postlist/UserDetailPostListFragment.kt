package com.kvw.jsonplaceholder.ui.userdetail.postlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kvw.jsonplaceholder.R
import com.kvw.jsonplaceholder.ui.userdetail.UserDetailViewModel

class UserDetailPostListFragment(val userListViewModel: UserDetailViewModel) : Fragment() {

    companion object {
        fun newInstance(userListViewModel: UserDetailViewModel) =
            UserDetailPostListFragment(
                userListViewModel
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_userdetail_postlist, container, false)
    }
}
