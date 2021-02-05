package com.mctryn.peacerungallery.model.data.photoset.remote

data class PhotosetResponse(
    val photosets: Photosets? = null,
    val stat: String? = null
)

data class Photosets(
    val perpage: Int? = null,
    val total: Int? = null,
    val pages: Int? = null,
    val page: Int? = null,
    val photoset: List<PhotosetItem?>? = null
)

data class PhotosetItem(
    val owner: String? = null,
    val server: String? = null,
    val visibilityCanSeeSet: Int? = null,
    val countPhotos: Int? = null,
    val dateUpdate: String? = null,
    val description: Description? = null,
    val countComments: String? = null,
    val videos: Int? = null,
    val secret: String? = null,
    val title: Title? = null,
    val needsInterstitial: Int? = null,
    val photos: Int? = null,
    val countViews: String? = null,
    val canComment: Int? = null,
    val dateCreate: String? = null,
    val countVideos: Int? = null,
    val farm: Int? = null,
    val id: String? = null,
    val username: String? = null,
    val primary: String? = null
)

data class Description(
    val _content: String? = null
)

data class Title(
    val _content: String? = null
)

