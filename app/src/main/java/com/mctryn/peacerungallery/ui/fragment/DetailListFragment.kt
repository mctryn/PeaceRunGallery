package com.mctryn.peacerungallery.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.core.view.doOnNextLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mctryn.peacerungallery.R
import com.mctryn.peacerungallery.databinding.FragmentDetailListBinding
import com.mctryn.peacerungallery.databinding.FragmentPhotosetListBinding
import com.mctryn.peacerungallery.model.data.ImageLink
import com.mctryn.peacerungallery.presentation.presenter.DetailPresenter
import com.mctryn.peacerungallery.presentation.view.DetailView
import com.mctryn.peacerungallery.ui.adapter.DetailRecyclerViewAdapter
import com.mctryn.peacerungallery.ui.fragment.base.BaseFragment
import com.mctryn.peacerungallery.ui.fragment.base.GridAutoFitLayoutManager
import moxy.ktx.moxyPresenter
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import javax.inject.Inject
import javax.inject.Provider

@StateStrategyType(AddToEndSingleStrategy::class)
class DetailListFragment : BaseFragment(R.layout.fragment_detail_list), DetailView {

    @Inject
    lateinit var presenterProvider: Provider<DetailPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    private val columnWidth = 100
    private lateinit var photosetId :String
    private var items: List<ImageLink> = ArrayList()
    private lateinit var recyclerViewAdapter: DetailRecyclerViewAdapter
    private lateinit var binding : FragmentDetailListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailListBinding.inflate(inflater, container, false)
        initRecyclerView(binding.recyclerViewDetails)
        setHasOptionsMenu(true)
        activity?.actionBar?.setDisplayShowHomeEnabled(true)
        return binding.root
    }

    private fun initRecyclerView(view: View) {
        if (view is RecyclerView) {
            with(view) {
                layoutManager = GridAutoFitLayoutManager(context, columnWidth)
                recyclerViewAdapter = DetailRecyclerViewAdapter(items)
                adapter = recyclerViewAdapter
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fromBundle = DetailListFragmentArgs.fromBundle(requireArguments())
        photosetId = fromBundle.photosetId
        presenter.getPhotos(photosetId)
        showLoadingIndicator();
        binding.swipeRefresh.setOnRefreshListener { onRefresh() }
    }

    override fun updateUi(photoItems: List<ImageLink>) {
        items = photoItems
        recyclerViewAdapter.addNewItem(photoItems)
        hideLoadingIndicator()
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
        presenter.getPhotos(photosetId)
    }

    private fun showLoadingIndicator() {
        binding.swipeRefresh.isRefreshing = true
    }

    override fun hideLoadingIndicator() {
        binding.swipeRefresh.isRefreshing = false
    }
}