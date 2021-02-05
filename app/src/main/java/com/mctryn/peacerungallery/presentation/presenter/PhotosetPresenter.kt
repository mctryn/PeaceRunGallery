package com.mctryn.peacerungallery.presentation.presenter

import com.mctryn.peacerungallery.model.data.photoset.contract.PhotosetRepositoryContract
import com.mctryn.peacerungallery.model.data.photoset.local.PhotosetItemLocal
import com.mctryn.peacerungallery.presentation.view.PhotosetView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
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
                .subscribe { photosetLocal, onError -> updateUi(photosetLocal.photoset) }
            disposal.add(subscribe)
        } else {
            updateUi()
        }
    }

    private fun updateUi(photosetResponse: List<PhotosetItemLocal>) {
        items = photosetResponse
        updateUi()
    }

    private fun updateUi() {
        viewState.updateUi(items)
        preloadImages(items)
    }

    private fun preloadImages(photosetResponse: List<PhotosetItemLocal>) {
        val subscribe = Observable.fromIterable(photosetResponse).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { viewState.cacheImage(it.getImageLink()) }
            .subscribe()
        disposal.add(subscribe)
    }
}