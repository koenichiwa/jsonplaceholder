package com.kvw.jsonplaceholder.ui.userdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.kvw.jsonplaceholder.MainActivity
import com.kvw.jsonplaceholder.R
import com.kvw.jsonplaceholder.ui.userdetail.albumlist.UserDetailAlbumList
import com.kvw.jsonplaceholder.ui.userdetail.postlist.UserDetailPostListFragment
import kotlinx.android.synthetic.main.fragment_userdetail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UserDetailFragment : Fragment() {
    private val navArgs: UserDetailFragmentArgs by navArgs()
    private val viewModel: UserDetailViewModel by viewModel { parametersOf(navArgs.user) }
    // private val postListFragment = UserDetailPostList.newInstance(viewModel)
    // private val albumListFragment = UserDetailAlbumList.newInstance(viewModel)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_userdetail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).binding.viewModel = viewModel

        setUpBottomNav()
    }

    private fun setUpBottomNav() {
        switchFragment(UserDetailAlbumList.newInstance(viewModel))
        bottomNavigation_userdetail.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menuItem_userDetail_posts -> {
                    switchFragment(UserDetailPostListFragment.newInstance(viewModel))
                    true
                }
                R.id.menuItem_userDetail_albums -> {
                    switchFragment(UserDetailAlbumList.newInstance(viewModel))
                    true
                }
                else -> { false }
            }
        }
    }

    private fun switchFragment(fragment: Fragment) {
        childFragmentManager
            .beginTransaction()
            .replace(frameLayout_userdetail.id, fragment)
            .commit()
    }
}
