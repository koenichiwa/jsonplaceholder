package com.kvw.jsonplaceholder.ui.userlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kvw.jsonplaceholder.R
import kotlinx.android.synthetic.main.fragment_userlist.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.kvw.jsonplaceholder.util.observe


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
            pendingView = activity?.findViewById<ProgressBar>(R.id.progressBar_main),
            onSuccess = { users ->
                recyclerView_userList.swapAdapter(
                    UserListAdapter(users) {
                        findNavController().navigate(
                            UserListFragmentDirections
                                .actionUserListFragmentToUserDetailFragment(it)
                        )
                    }, true) },
            onError = { _, reason -> Snackbar.make(frameLayout_userList_root, reason, Snackbar.LENGTH_LONG) }
        )
    }
}
