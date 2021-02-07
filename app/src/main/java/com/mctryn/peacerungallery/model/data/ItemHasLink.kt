package com.mctryn.peacerungallery.model.data

abstract class ItemHasLink(
    private val previewId: String,
    private val previewServerId: String,
    private val previewSecret: String
) {
    fun getImageLink(): String {
        return "https://live.staticflickr.com/${previewServerId}/${previewId}_${previewSecret}_z.jpg"
    }
}
