package com.mctryn.peacerungallery.presentation.presenter

import com.mctryn.peacerungallery.model.data.photosetDetail.contarct.PhotosetDetailsRepositoryContract
import com.mctryn.peacerungallery.model.data.photosetDetail.local.PhotosetDetailItemLocal
import com.mctryn.peacerungallery.model.data.photosetDetail.local.PhotosetDetailLocal
import com.mctryn.peacerungallery.presentation.view.DetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val repository: PhotosetDetailsRepositoryContract) :
    MvpPresenter<DetailView>() {
    private var items: List<PhotosetDetailItemLocal> = ArrayList()
    private var disposal: CompositeDisposable = CompositeDisposable()

    fun getPhotos(photosetId: String) {
        if (items.isEmpty()) {
            val photoItemsSingle = repository.getPhotoItems(photosetId)

            val subscribe = photoItemsSingle.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { photosetDetailLocal, onError ->
                    if (onError == null) {
                        onDataReceived(photosetDetailLocal)
                    } else {
                        onErrorOccurred(onError)
                    }
                }
            disposal.add(subscribe)
        } else {
            updateUi()
        }
    }

    private fun onDataReceived(photosetDetail: PhotosetDetailLocal) {
        items = photosetDetail.photosetDetailItems
        updateUi()
        preloadImages(photosetDetail.photosetDetailLinks)
    }

    private fun updateUi() {
        viewState.updateUi(items)
    }

    private fun preloadImages(photosetLinks: List<String>) {
        photosetLinks.forEach { viewState.cacheImage(it) }
    }

    private fun onErrorOccurred(error: Throwable) {
        viewState.onErrorOccurred(error.localizedMessage!!)
    }

    override fun onDestroy() {
        disposal.dispose()
        super.onDestroy()
    }
}