package com.mctryn.peacerungallery.model.data.photoset

import com.mctryn.peacerungallery.model.FlickrApi
import com.mctryn.peacerungallery.model.data.photoset.contract.PhotosetRepository
import com.mctryn.peacerungallery.model.data.photoset.local.PhotosetLocal
import com.mctryn.peacerungallery.model.data.photoset.remote.PhotosetItem
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class PhotosetRepositoryImpl @Inject constructor(
    private val flickrApi: FlickrApi,
    val photosetMapper: PhotosetMapper
) :
    PhotosetRepository {

    override fun getPhotosetItems(): Single<PhotosetLocal> {
        return flickrApi.getPhotoSetItems()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { photosetMapper(it.photosets?.photoset as List<PhotosetItem>) }
            .map { PhotosetLocal(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}