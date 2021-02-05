package com.mctryn.peacerungallery.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.mctryn.peacerungallery.R
import com.mctryn.peacerungallery.model.data.photoset.local.PhotosetItemLocal
import com.mctryn.peacerungallery.presentation.presenter.PhotosetPresenter
import com.mctryn.peacerungallery.presentation.view.PhotosetView
import com.mctryn.peacerungallery.ui.adapter.PhotosetRecyclerViewAdapter
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import javax.inject.Inject
import javax.inject.Provider

@StateStrategyType(AddToEndSingleStrategy::class)
class PhotosetListFragment : MvpAppCompatFragment(R.layout.fragment_photoset_list), PhotosetView,
    PhotosetRecyclerViewAdapter.OnItemClickListener {

    @Inject
    lateinit var presenterProvider: Provider<PhotosetPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    private val columnCount = 2
    private var items: List<PhotosetItemLocal> = ArrayList()
    private lateinit var recyclerViewAdapter: PhotosetRecyclerViewAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_photoset_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = GridLayoutManager(context, columnCount)

                recyclerViewAdapter = PhotosetRecyclerViewAdapter(items, this@PhotosetListFragment)
                adapter = recyclerViewAdapter
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getItems()
    }

    override fun updateUi(photosetItems: List<PhotosetItemLocal>) {
        items = photosetItems
        recyclerViewAdapter.addNewItem(items)
    }

    override fun cacheImage(imageLink: String) {
        Glide.with(this)
            .load(imageLink)
            .apply(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            )
            .preload()
    }

    override fun onItemClicked(position: Int) {
        navigateToDetailsFragment(position)
    }

    private fun navigateToDetailsFragment(id: Int) {
        val photosetId = items[id].id
        val action =
            PhotosetListFragmentDirections.photosetFragmentToDetailListFragment(photosetId)

        findNavController().navigate(action)
    }
}