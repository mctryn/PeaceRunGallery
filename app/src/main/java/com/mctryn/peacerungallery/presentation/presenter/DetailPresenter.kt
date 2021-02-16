package com.mctryn.peacerungallery.presentation.presenter

import com.mctryn.peacerungallery.model.data.photosetDetail.contarct.PhotosetDetailsRepository
import com.mctryn.peacerungallery.model.data.photosetDetail.local.PhotosetDetailLocal
import com.mctryn.peacerungallery.presentation.presenter.base.BasePresenter
import com.mctryn.peacerungallery.presentation.view.DetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val repository: PhotosetDetailsRepository) :
    BasePresenter<DetailView>() {

    fun getPhotos(photosetId: String) {
        if (items.isEmpty()) {
            val photoItemsSingle = repository.getPhotoItems(photosetId)

            val subscribe = photoItemsSingle.observeOn(AndroidSchedulers.mainThread())
                .subscribe({ photosetDetailLocal ->
                    onDataReceived(photosetDetailLocal)
                }, { onError ->
                    onErrorOccurred(onError)
                })
            super.disposal.add(subscribe)
        } else {
            updateUi()
        }
    }

    protected fun onDataReceived(photosetDetail: PhotosetDetailLocal) {
        items = photosetDetail.photosetDetailItems
        updateUi()
    }
}