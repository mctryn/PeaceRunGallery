package com.mctryn.peacerungallery.presentation.presenter

import com.mctryn.peacerungallery.model.data.photoset.contract.PhotosetRepositoryContract
import com.mctryn.peacerungallery.model.data.photoset.local.PhotosetLocal
import com.mctryn.peacerungallery.presentation.presenter.base.BasePresenter
import com.mctryn.peacerungallery.presentation.view.PhotosetView
import javax.inject.Inject

class PhotosetPresenter @Inject constructor(private val repository: PhotosetRepositoryContract) :
    BasePresenter<PhotosetView>() {

    fun getItems() {
        if (items.isEmpty()) {
            val photosetItemsSingle = repository.getPhotosetItems()

            val subscribe = photosetItemsSingle
                .subscribe({ photosetLocal ->
                    onDataReceived(photosetLocal)
                }, { onError: Throwable ->
                    onErrorOccurred(onError)
                })
            super.disposal.add(subscribe)
        } else {
            updateUi()
        }
    }

    private fun onDataReceived(photosetLocal: PhotosetLocal) {
        items = photosetLocal.photoset
        updateUi()
        preloadImages(photosetLocal.photosetPreviewLinks)
    }
}