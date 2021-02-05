package com.mctryn.peacerungallery.model.data.photosetDetail.remote

data class PhotosetDetailResponse(
	val stat: String? = null,
	val photoset: Photoset? = null
)

data class PhotoItem(
	val server: String? = null,
	val ispublic: Int? = null,
	val isfriend: Int? = null,
	val farm: Int? = null,
	val id: String? = null,
	val secret: String? = null,
	val title: String? = null,
	val isprimary: String? = null,
	val isfamily: Int? = null
)

data class Photoset(
	val owner: String? = null,
	val perPage: Int? = null,
	val perpage: Int? = null,
	val total: Int? = null,
	val pages: Int? = null,
	val ownername: String? = null,
	val photo: List<PhotoItem?>? = null,
	val id: String? = null,
	val page: Int? = null,
	val title: String? = null,
	val primary: String? = null
)

