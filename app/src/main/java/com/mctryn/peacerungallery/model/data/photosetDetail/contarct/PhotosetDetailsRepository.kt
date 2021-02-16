package com.mctryn.peacerungallery.model.data.photosetDetail.contarct

import com.mctryn.peacerungallery.model.data.photosetDetail.local.PhotosetDetailLocal
import io.reactivex.Single

interface PhotosetDetailsRepository {
    fun getPhotoItems(photosetId: String) : Single<PhotosetDetailLocal>
}