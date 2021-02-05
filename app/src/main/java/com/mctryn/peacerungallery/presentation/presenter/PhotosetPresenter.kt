package com.mctryn.peacerungallery.presentation.presenter

import com.mctryn.peacerungallery.model.data.photoset.contract.PhotosetRepositoryContract
import com.mctryn.peacerungallery.model.data.photoset.local.PhotosetItemLocal
import com.mctryn.peacerungallery.model.data.photoset.local.PhotosetLocal
import com.mctryn.peacerungallery.presentation.view.PhotosetView
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class PhotosetPresenter @Inject constructor(val repository: PhotosetRepositoryContract) :
    MvpPresenter<PhotosetView>() {
    private var items: List<PhotosetItemLocal> = ArrayList()
    private var disposal: CompositeDisposable = CompositeDisposable()


    fun getItems() {

        if (items.size.equals(0)) {
            val photosetItemsSingle = repository.getPhotosetItems()

            val subscribe = photosetItemsSingle
                .subscribe { photosetLocal, onError -> onDataRecived(photosetLocal) }
            disposal.add(subscribe)
        } else {
            updateUi()
        }
    }

    private fun onDataRecived(photosetLocal: PhotosetLocal) {
        items = photosetLocal.photoset
        updateUi()
        preloadImages(photosetLocal.photosetPreviewLinks)
    }

    private fun updateUi() {
        viewState.updateUi(items)

    }

    private fun preloadImages(photosetLinks: List<String>) {
        photosetLinks.forEach { viewState.cacheImage(it) }
    }
}