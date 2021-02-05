package com.mctryn.peacerungallery.presentation.view

import com.mctryn.peacerungallery.model.data.photoset.local.PhotosetItemLocal
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface PhotosetView : MvpView {
    fun updateUi(photosetItems: List<PhotosetItemLocal>)
    fun cacheImage(imageLink: String)
}