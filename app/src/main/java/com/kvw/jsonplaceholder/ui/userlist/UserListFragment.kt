package com.kvw.jsonplaceholder.ui.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kvw.jsonplaceholder.MainActivity
import com.kvw.jsonplaceholder.R
import kotlinx.android.synthetic.main.fragment_userlist.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class UserListFragment : Fragment() {

    private val viewModel: UserListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_userlist, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).binding.viewModel = viewModel

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        viewModel.users.observe(this.viewLifecycleOwner, Observer { users ->
            recyclerView_userList.swapAdapter(
                UserListAdapter(users) {
                    findNavController().navigate(
                        UserListFragmentDirections
                            .actionUserListFragmentToUserDetailFragment(it)
                    )
                }, true) }
        )
    }
}
