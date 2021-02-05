package com.mctryn.peacerungallery.model.data.photoset.contract

import com.mctryn.peacerungallery.model.data.photoset.local.PhotosetLocal
import com.mctryn.peacerungallery.model.data.photoset.remote.PhotosetResponse
import io.reactivex.Single

interface PhotosetRepositoryContract {
    fun getPhotosetItems(): Single<PhotosetLocal>
}