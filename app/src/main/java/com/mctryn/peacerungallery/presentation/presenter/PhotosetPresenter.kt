package com.mctryn.peacerungallery.presentation.presenter

import com.mctryn.peacerungallery.model.data.photoset.contract.PhotosetRepositoryContract
import com.mctryn.peacerungallery.model.data.photoset.local.PhotosetItemLocal
import com.mctryn.peacerungallery.model.data.photoset.local.PhotosetLocal
import com.mctryn.peacerungallery.presentation.view.PhotosetView
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter
import javax.inject.Inject

class PhotosetPresenter @Inject constructor(private val repository: PhotosetRepositoryContract) :
    MvpPresenter<PhotosetView>() {
    private var items: List<PhotosetItemLocal> = ArrayList()
    private var disposal: CompositeDisposable = CompositeDisposable()


    fun getItems() {
        if (items.isEmpty()) {
            val photosetItemsSingle = repository.getPhotosetItems()

            val subscribe = photosetItemsSingle
                .subscribe { photosetLocal, onError ->
                    if (onError == null) {
                        onDataReceived(photosetLocal)
                    } else {
                        onErrorOccurred(onError)
                    }
                }
            disposal.add(subscribe)
        } else {
            updateUi()
        }
    }

    private fun onErrorOccurred(error: Throwable) {
        viewState.onErrorOccurred(error.localizedMessage!!)
    }

    private fun onDataReceived(photosetLocal: PhotosetLocal) {
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

    override fun onDestroy() {
        disposal.dispose()
        super.onDestroy()
    }
}