package com.mctryn.peacerungallery.presentation.view

import com.mctryn.peacerungallery.model.data.photosetDetail.local.PhotosetDetailItemLocal
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface DetailView : MvpView {
    fun updateUi(photoItems: List<PhotosetDetailItemLocal>)
}