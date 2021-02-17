package com.mctryn.peacerungallery.model.data.photoset.local

import com.mctryn.peacerungallery.model.data.ImageLink

data class PhotosetLocal(
    val photoset: List<PhotosetItemLocal>,
) {
    val photosetLinks: List<String> = photoset.map { item -> item.getImageLink() }
}

data class PhotosetItemLocal(
    val server: String,
    val description: String,
    val title: String,
    val photos: Int,
    val primary: String,
    val secret: String,
    val id: String
) : ImageLink(primary, id, secret)
