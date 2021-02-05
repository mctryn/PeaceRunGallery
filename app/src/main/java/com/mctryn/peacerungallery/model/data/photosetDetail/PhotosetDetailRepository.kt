package com.mctryn.peacerungallery.model.data.photosetDetail

import com.mctryn.peacerungallery.model.FlickrApi
import com.mctryn.peacerungallery.model.data.photosetDetail.contarct.PhotosetDetailsRepositoryContract
import com.mctryn.peacerungallery.model.data.photosetDetail.local.PhotosetDetailLocal
import com.mctryn.peacerungallery.model.data.photosetDetail.remote.PhotoItem
import io.reactivex.Single
import javax.inject.Inject

class PhotosetDetailRepository @Inject constructor(
    val flickrApi: FlickrApi,
    val photosetDetailMapper: PhotosetDetailMapper
) :
    PhotosetDetailsRepositoryContract {

    override fun getPhotoItems(photosetId: String): Single<PhotosetDetailLocal> {
        return flickrApi.getPhotos(photosetId)
            .map { photosetDetailMapper(it.photoset?.photo as List<PhotoItem>) }
            .map { PhotosetDetailLocal(it) }
    }
}