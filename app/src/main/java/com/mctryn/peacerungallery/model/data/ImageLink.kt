package com.mctryn.peacerungallery.model.data

abstract class ImageLink(
    private val previewId: String,
    val previewPhotosetId: String,
    private val previewSecret: String
) {
    fun getImageLink(): String {
        return "https://live.staticflickr.com/${previewPhotosetId}/${previewId}_${previewSecret}_z.jpg"
    }
}
