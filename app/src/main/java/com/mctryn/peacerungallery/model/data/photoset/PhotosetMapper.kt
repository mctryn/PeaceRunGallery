package com.mctryn.peacerungallery.model.data.photoset

import com.mctryn.peacerungallery.model.data.photoset.local.PhotosetItemLocal
import com.mctryn.peacerungallery.model.data.photoset.remote.PhotosetItem
import javax.inject.Inject

open class PhotosetMapper @Inject constructor() {
    open operator fun invoke(entities: List<PhotosetItem>): List<PhotosetItemLocal> =
        entities
            .map { toPhoto(it) }

    open fun toPhoto(entity: PhotosetItem): PhotosetItemLocal = PhotosetItemLocal(
        entity.server!!,
        entity.description?._content!!,
        entity.title?._content!!,
        entity.photos!!,
        entity.primary!!,
        entity.secret!!,
        entity.id!!
    )
}