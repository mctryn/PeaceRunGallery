package com.mctryn.peacerungallery.model.data.photosetDetail.contarct

import com.mctryn.peacerungallery.model.data.photosetDetail.local.PhotosetDetailLocal
import com.mctryn.peacerungallery.model.data.photosetDetail.remote.PhotosetDetailResponse
import io.reactivex.Single

interface PhotosetDetailsRepositoryContract {
    fun getPhotoItems(photosetId: String) : Single<PhotosetDetailLocal>
}