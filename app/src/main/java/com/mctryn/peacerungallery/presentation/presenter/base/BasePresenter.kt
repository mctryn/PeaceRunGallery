package com.mctryn.peacerungallery.presentation.presenter.base

import com.mctryn.peacerungallery.model.data.ImageLink
import com.mctryn.peacerungallery.presentation.view.BaseView
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter

abstract class BasePresenter<View : BaseView> : MvpPresenter<View>() {

    protected var items: List<ImageLink> = ArrayList()
    protected var disposal: CompositeDisposable = CompositeDisposable()

    protected fun updateUi() {
        viewState.updateUi(items)
    }

    protected fun preloadImages(photosetLinks: List<String>) {
        photosetLinks.forEach { viewState.cacheImage(it) }
    }

    protected fun onErrorOccurred(error: Throwable) {
        viewState.onErrorOccurred(error.localizedMessage!!)
    }

    override fun onDestroy() {
        disposal.dispose()
        super.onDestroy()
    }
}