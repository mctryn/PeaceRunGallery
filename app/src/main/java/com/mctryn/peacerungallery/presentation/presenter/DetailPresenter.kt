package com.mctryn.peacerungallery.presentation.presenter

import com.mctryn.peacerungallery.model.data.photosetDetail.contarct.PhotosetDetailsRepositoryContract
import com.mctryn.peacerungallery.model.data.photosetDetail.local.PhotosetDetailItemLocal
import com.mctryn.peacerungallery.presentation.view.DetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val repository: PhotosetDetailsRepositoryContract) :
    MvpPresenter<DetailView>() {

    fun getPhotos(photosetId: String) {
        val photoItemsSingle = repository.getPhotoItems(photosetId)

        val subscribe = photoItemsSingle.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { photosetDetailItem, onError -> updateUi(photosetDetailItem.photosetDetailItems) }
    }

    private fun updateUi(photosetDetail: List<PhotosetDetailItemLocal>) {
        //TODO CHECK RESULT
        viewState.updateUi(photosetDetail)
    }
}