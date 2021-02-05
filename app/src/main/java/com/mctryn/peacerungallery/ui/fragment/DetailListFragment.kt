package com.mctryn.peacerungallery.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mctryn.peacerungallery.R
import com.mctryn.peacerungallery.model.data.photosetDetail.local.PhotosetDetailItemLocal
import com.mctryn.peacerungallery.presentation.presenter.DetailPresenter
import com.mctryn.peacerungallery.presentation.view.DetailView
import com.mctryn.peacerungallery.ui.adapter.DetailRecyclerViewAdapter
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

    private val columnCount = 1
    private var items: List<PhotosetDetailItemLocal> = ArrayList()
    private lateinit var recyclerViewAdapter: DetailRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_list, container, false)
        initRecyclerView(view)
        return view
    }

    private fun initRecyclerView(view: View) {
        if (view is RecyclerView) {
            with(view) {
                layoutManager = GridLayoutManager(context, columnCount)
                recyclerViewAdapter = DetailRecyclerViewAdapter(items)
                adapter = recyclerViewAdapter
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fromBundle = DetailListFragmentArgs.fromBundle(requireArguments())
        val photosetId = fromBundle.photosetId
        presenter.getPhotos(photosetId)
    }

    override fun updateUi(photoItems: List<PhotosetDetailItemLocal>) {
        items = photoItems
        recyclerViewAdapter.addNewItem(photoItems)
    }

    override fun cacheImage(imageLink: String) {
        preloadImage(imageLink)
    }
}