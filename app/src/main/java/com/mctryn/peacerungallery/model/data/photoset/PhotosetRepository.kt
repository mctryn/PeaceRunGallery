package com.mctryn.peacerungallery.model.data.photoset

import com.mctryn.peacerungallery.model.FlickrApi
import com.mctryn.peacerungallery.model.data.photoset.contract.PhotosetRepositoryContract
import com.mctryn.peacerungallery.model.data.photoset.local.PhotosetItemLocal
import com.mctryn.peacerungallery.model.data.photoset.local.PhotosetLocal
import com.mctryn.peacerungallery.model.data.photoset.remote.PhotosetItem
import com.mctryn.peacerungallery.model.data.photoset.remote.PhotosetResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PhotosetRepository @Inject constructor(
    val flickrApi: FlickrApi,
    val photosetMapper: PhotosetMapper
) :
    PhotosetRepositoryContract {

    override fun getPhotosetItems(): Single<PhotosetLocal> {
        return flickrApi.getPhotoSetItems()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { photosetMapper(it.photosets?.photoset as List<PhotosetItem>) }
            .map { PhotosetLocal(it) }
    }
}