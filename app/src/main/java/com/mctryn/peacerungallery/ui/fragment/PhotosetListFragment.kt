package com.mctryn.peacerungallery.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mctryn.peacerungallery.R
import com.mctryn.peacerungallery.model.data.ImageLink
import com.mctryn.peacerungallery.presentation.presenter.PhotosetPresenter
import com.mctryn.peacerungallery.presentation.view.PhotosetView
import com.mctryn.peacerungallery.ui.adapter.PhotosetRecyclerViewAdapter
import com.mctryn.peacerungallery.ui.fragment.base.BaseFragment
import moxy.ktx.moxyPresenter
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import javax.inject.Inject
import javax.inject.Provider


@StateStrategyType(AddToEndSingleStrategy::class)
class PhotosetListFragment : BaseFragment(R.layout.fragment_photoset_list), PhotosetView {

    @Inject
    lateinit var presenterProvider: Provider<PhotosetPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    private val columnCount = 2
    private var items: List<ImageLink> = ArrayList()
    private lateinit var recyclerViewAdapter: PhotosetRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_photoset_list, container, false)
        initRecyclerView(view)
        setHasOptionsMenu(true)
        return view
    }

    private fun initRecyclerView(view: View) {
        if (view is RecyclerView) {
            with(view) {
                layoutManager = GridLayoutManager(context, columnCount)
                recyclerViewAdapter = PhotosetRecyclerViewAdapter(items, onItemClickListener)
                adapter = recyclerViewAdapter
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getItems()
    }

    override fun updateUi(photosetItems: List<ImageLink>) {
        items = photosetItems
        recyclerViewAdapter.addNewItem(items)
    }

    private val onItemClickListener: PhotosetRecyclerViewAdapter.OnItemClickListener =
        PhotosetRecyclerViewAdapter.OnItemClickListener {
            navigateToDetailsFragment(it)
        }


    private fun navigateToDetailsFragment(id: Int) {
        val photosetId = items[id].previewPhotosetId
        val action =
            PhotosetListFragmentDirections.photosetFragmentToDetailListFragment(photosetId)

        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_refresh) {
            presenter.getItems()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}