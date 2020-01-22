package com.kvw.jsonplaceholder.ui.userdetail.postlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kvw.jsonplaceholder.R
import com.kvw.jsonplaceholder.ui.userdetail.UserDetailViewModel

class UserDetailPostList(val userListViewModel: UserDetailViewModel) : Fragment() {

    companion object {
        fun newInstance(userListViewModel: UserDetailViewModel) =
            UserDetailPostList(
                userListViewModel
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_userdetail_postlist, container, false)
    }

}
