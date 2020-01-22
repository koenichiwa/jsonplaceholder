package com.kvw.jsonplaceholder.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.kvw.jsonplaceholder.R
import com.kvw.jsonplaceholder.util.Intel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_userlist.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


@ExperimentalCoroutinesApi
class UserListFragment : Fragment() {

    private val viewModel: UserListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_userlist, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        viewModel.users.observe(this,
            Observer {
                when(it){
                    is Intel.Pending -> {
                        Timber.d("Waiting for users")
                        progressBar_main.visibility = View.VISIBLE
                    }
                    is Intel.Success -> {
                        recyclerView_userList.swapAdapter(UserListAdapter(it.data) {}, true)
                        Timber.d("Swapped Adapter, new list size: ${it.data.size}")
                        if(it.source == Intel.Source.Remote)
                            progressBar_main.visibility = View.GONE
                    }
                    is Intel.Error -> {
                        Timber.e(it.throwable, it.reason)
                        Snackbar.make(frameLayout_userList_root, it.reason, Snackbar.LENGTH_LONG)
                    }
                }
            }
        )
    }
}
