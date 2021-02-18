package com.mctryn.peacerungallery.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mctryn.peacerungallery.R
import com.mctryn.peacerungallery.databinding.FragmentPhotosetListBinding
import com.mctryn.peacerungallery.model.data.ImageLink
import com.mctryn.peacerungallery.presentation.presenter.PhotosetPresenter
import com.mctryn.peacerungallery.presentation.view.PhotosetView
import com.mctryn.peacerungallery.ui.adapter.PhotosetRecyclerViewAdapter
import com.mctryn.peacerungallery.ui.fragment.base.BaseFragment
import com.mctryn.peacerungallery.ui.fragment.base.GridAutoFitLayoutManager
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

    private val columnWidth = 150
    private var items: List<ImageLink> = ArrayList()
    private lateinit var recyclerViewAdapter: PhotosetRecyclerViewAdapter
    private lateinit var binding: FragmentPhotosetListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotosetListBinding.inflate(inflater, container, false)
        initRecyclerView(binding.recyclerView)
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun initRecyclerView(view: View) {
        if (view is RecyclerView) {
            with(view) {
                layoutManager = GridAutoFitLayoutManager(context, columnWidth)
                recyclerViewAdapter = PhotosetRecyclerViewAdapter(items, onItemClickListener)
                adapter = recyclerViewAdapter
            }
        }
    }

    private val onItemClickListener: PhotosetRecyclerViewAdapter.OnItemClickListener =
        PhotosetRecyclerViewAdapter.OnItemClickListener {
            navigateToDetailsFragment(it)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getItems()
        showLoadingIndicator()
        binding.swipeRefresh.setOnRefreshListener { onRefresh() }
    }

    override fun updateUi(photosetItems: List<ImageLink>) {
        items = photosetItems
        recyclerViewAdapter.addNewItem(items)
        hideLoadingIndicator()
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
            onRefresh()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onRefresh() {
        showLoadingIndicator()
        presenter.getItems()
    }

    private fun showLoadingIndicator() {
        binding.swipeRefresh.isRefreshing = true
    }

    override fun hideLoadingIndicator() {
        binding.swipeRefresh.isRefreshing = false
    }
}