package com.mctryn.peacerungallery.model.data.photoset.contract

import com.mctryn.peacerungallery.model.data.photoset.local.PhotosetLocal
import io.reactivex.Single

interface PhotosetRepository {
    fun getPhotosetItems(): Single<PhotosetLocal>
}