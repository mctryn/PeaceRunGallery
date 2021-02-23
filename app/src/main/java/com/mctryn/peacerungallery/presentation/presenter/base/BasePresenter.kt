package com.mctryn.peacerungallery.presentation.presenter.base

import com.mctryn.peacerungallery.model.data.ImageLink
import com.mctryn.peacerungallery.presentation.view.BaseView
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

abstract class BasePresenter<View : BaseView> : MvpPresenter<View>() {

    protected var items: List<ImageLink> = ArrayList()
    protected var disposal: CompositeDisposable = CompositeDisposable()

    protected fun updateUi(itemList: List<ImageLink>) {
        viewState.updateUi(itemList)
    }

    protected fun onErrorOccurred(error: Throwable) {
        viewState.onErrorOccurred(error.localizedMessage!!)
    }

    override fun onDestroy() {
        disposal.dispose()
        super.onDestroy()
    }

    protected fun onDataReceived(itemList: List<ImageLink>, linkList: List<String> ) {
        items = itemList
        updateUi(itemList)
        preloadImage(linkList)
    }

    private fun preloadImage(imageLinks: List<String>) {
        prepareFlowable(imageLinks)?.let { disposal.add(it) }
    }

    private fun prepareFlowable(imageLinks: List<String>): Disposable? {

        return Flowable.fromIterable(imageLinks)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .parallel()
            .map { viewState.preloadImage(it) }
            .sequential()
            .subscribe()
    }
}