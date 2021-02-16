package com.mctryn.peacerungallery.di.network

import com.mctryn.peacerungallery.model.data.photoset.PhotosetRepositoryImpl
import com.mctryn.peacerungallery.model.data.photoset.contract.PhotosetRepository
import com.mctryn.peacerungallery.model.data.photosetDetail.PhotosetDetailRepositoryImpl
import com.mctryn.peacerungallery.model.data.photosetDetail.contarct.PhotosetDetailsRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindPhotosetRepository(photosetRepository: PhotosetRepositoryImpl): PhotosetRepository

    @Binds
    fun bindPhotosetDetailsRepository(photosetDetailsRepository: PhotosetDetailRepositoryImpl): PhotosetDetailsRepository

}