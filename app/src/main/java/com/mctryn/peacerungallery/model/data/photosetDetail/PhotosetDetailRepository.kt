package com.mctryn.peacerungallery.model.data.photosetDetail

import com.mctryn.peacerungallery.model.FlickrApi
import com.mctryn.peacerungallery.model.data.photoset.local.PhotosetLocal
import com.mctryn.peacerungallery.model.data.photoset.remote.PhotosetItem
import com.mctryn.peacerungallery.model.data.photosetDetail.contarct.PhotosetDetailsRepositoryContract
import com.mctryn.peacerungallery.model.data.photosetDetail.local.PhotosetDetailLocal
import com.mctryn.peacerungallery.model.data.photosetDetail.remote.PhotoItem
import com.mctryn.peacerungallery.model.data.photosetDetail.remote.PhotosetDetailResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PhotosetDetailRepository @Inject constructor(
    val flickrApi: FlickrApi,
    val photosetDetailMapper: PhotosetDetailMapper
) :
    PhotosetDetailsRepositoryContract {

    override fun getPhotoItems(photosetId: String): Single<PhotosetDetailLocal> {
        return flickrApi.getPhotos(photosetId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { photosetDetailMapper(it.photoset?.photo as List<PhotoItem>) }
            .map { PhotosetDetailLocal(it) }
    }
}