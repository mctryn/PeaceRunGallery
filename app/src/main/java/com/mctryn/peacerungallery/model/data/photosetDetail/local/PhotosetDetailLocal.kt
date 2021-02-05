package com.mctryn.peacerungallery.model.data.photosetDetail.local

import com.mctryn.peacerungallery.model.data.ItemHasLink

data class PhotosetDetailLocal(
    val photosetDetailItems: List<PhotosetDetailItemLocal>
) {
    val photosetDetailLinks: List<String> = photosetDetailItems.map { item -> item.getImageLink() }
}

data class PhotosetDetailItemLocal(
    val server: String,
    val id: String,
    val secret: String,
) : ItemHasLink(id, server, secret)