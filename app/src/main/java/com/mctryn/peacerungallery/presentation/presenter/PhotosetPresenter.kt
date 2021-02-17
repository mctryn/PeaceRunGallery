package com.mctryn.peacerungallery.presentation.presenter

import com.mctryn.peacerungallery.model.data.photoset.contract.PhotosetRepository
import com.mctryn.peacerungallery.model.data.photoset.local.PhotosetLocal
import com.mctryn.peacerungallery.presentation.presenter.base.BasePresenter
import com.mctryn.peacerungallery.presentation.view.PhotosetView
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class PhotosetPresenter @Inject constructor(private val repository: PhotosetRepository) :
    BasePresenter<PhotosetView>() {

    fun getItems() {
        if (items.isEmpty()) {
            val photosetItemsSingle = repository.getPhotosetItems()

            val subscribe = photosetItemsSingle.observeOn(AndroidSchedulers.mainThread())
                .subscribe({ photosetLocal ->
                    onDataReceived(photosetLocal)
                }, { onError: Throwable ->
                    onErrorOccurred(onError)
                })
            super.disposal.add(subscribe)
        } else {
            updateUi(items)
        }
    }

    private fun onDataReceived(itemList: PhotosetLocal) {
        onDataReceived(itemList.photoset, itemList.photosetLinks)
    }

}
