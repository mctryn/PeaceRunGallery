package com.mctryn.peacerungallery.model

import com.mctryn.peacerungallery.model.data.photosetDetail.remote.PhotosetDetailResponse
import com.mctryn.peacerungallery.model.data.photoset.remote.PhotosetResponse
import com.mctryn.peacerungallery.utils.FlickrUtils.Companion.FLICKR_API_Key
import com.mctryn.peacerungallery.utils.FlickrUtils.Companion.USER_ID
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface FlickrApi {

    @GET("services/rest/?method=flickr.photosets.getList&format=json&nojsoncallback=1")
    fun getPhotoSetItems(
        @Query("user_id") userId: String = USER_ID,
        @Query("api_key") apiKey: String = FLICKR_API_Key
    ): Single<PhotosetResponse>

    @GET("services/rest/?method=flickr.photosets.getPhotos&format=json&nojsoncallback=1")
    fun getPhotos(
        @Query("photoset_id") photosetId: String,
        @Query("user_id") userId: String = USER_ID,
        @Query("api_key") apiKey: String = FLICKR_API_Key
    ): Single<PhotosetDetailResponse>
}
