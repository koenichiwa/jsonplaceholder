package com.kvw.jsonplaceholder.ui.albumdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.kvw.jsonplaceholder.MainActivity
import com.kvw.jsonplaceholder.R
import kotlinx.android.synthetic.main.fragment_albumdetail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AlbumDetailFragment : Fragment() {

    private val navArgs: AlbumDetailFragmentArgs by navArgs()
    private val viewModel: AlbumDetailViewModel by viewModel { parametersOf(navArgs.album) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_albumdetail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).binding.viewModel = viewModel

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        recyclerView_albumDetail.layoutManager = GridLayoutManager(context, 2)

        viewModel.photos.observe(this, Observer {
            recyclerView_albumDetail.swapAdapter(AlbumDetailPhotoListAdapter(it) {}, true)
        })
    }
}
