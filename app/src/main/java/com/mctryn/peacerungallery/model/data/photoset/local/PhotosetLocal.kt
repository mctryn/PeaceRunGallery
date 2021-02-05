package com.mctryn.peacerungallery.model.data.photoset.local

import com.mctryn.peacerungallery.model.data.ItemHasLink

data class PhotosetLocal(
    val photoset: List<PhotosetItemLocal>,
) {
    val photosetPreviewLinks: List<String> = photoset.map { item -> item.getImageLink() }
}

data class PhotosetItemLocal(
    val server: String,
    val description: String,
    val title: String,
    val photos: Int,
    val primary: String,
    val secret: String,
    val id: String
) : ItemHasLink(primary, id, secret)
