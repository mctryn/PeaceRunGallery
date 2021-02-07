package com.mctryn.peacerungallery.model.data.photosetDetail

import com.mctryn.peacerungallery.model.data.photosetDetail.local.PhotosetDetailItemLocal
import com.mctryn.peacerungallery.model.data.photosetDetail.remote.PhotoItem
import javax.inject.Inject

class PhotosetDetailMapper @Inject constructor() {
    operator fun invoke(entities: List<PhotoItem>): List<PhotosetDetailItemLocal> =
        entities
            .map { toPhoto(it) }

    private fun toPhoto(entity: PhotoItem): PhotosetDetailItemLocal = PhotosetDetailItemLocal(
        entity.server!!,
        entity.id!!,
        entity.secret!!
    )
}
