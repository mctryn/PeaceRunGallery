package com.mctryn.peacerungallery.presentation.view

import com.mctryn.peacerungallery.model.data.ImageLink
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BaseView : MvpView {
    fun updateUi(photoItems: List<ImageLink>)
    fun onErrorOccurred(error: String)
}