package com.mctryn.peacerungallery.model.data

abstract class ItemHasLink(
    val previewId: String,
    val previewServerId: String,
    val previewSecret: String
) {
    fun getImageLink(): String {
        return "https://live.staticflickr.com/${previewServerId}/${previewId}_${previewSecret}_z.jpg"
    }
}
