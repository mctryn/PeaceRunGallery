package com.mctryn.peacerungallery.model.data.photosetDetail

import com.mctryn.peacerungallery.model.FlickrApi
import com.mctryn.peacerungallery.model.data.photosetDetail.contarct.PhotosetDetailsRepository
import com.mctryn.peacerungallery.model.data.photosetDetail.local.PhotosetDetailLocal
import com.mctryn.peacerungallery.model.data.photosetDetail.remote.PhotoItem
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class PhotosetDetailRepositoryImpl @Inject constructor(
    private val flickrApi: FlickrApi,
    val photosetDetailMapper: PhotosetDetailMapper
) :
    PhotosetDetailsRepository {

    override fun getPhotoItems(photosetId: String): Single<PhotosetDetailLocal> {
        return flickrApi.getPhotos(photosetId)
            .subscribeOn(Schedulers.io())
            .map { photosetDetailMapper(it.photoset?.photo as List<PhotoItem>) }
            .map { PhotosetDetailLocal(it) }
    }
}