package com.mctryn.peacerungallery.di.network

import com.mctryn.peacerungallery.model.data.photoset.PhotosetRepository
import com.mctryn.peacerungallery.model.data.photoset.contract.PhotosetRepositoryContract
import com.mctryn.peacerungallery.model.data.photosetDetail.PhotosetDetailRepository
import com.mctryn.peacerungallery.model.data.photosetDetail.contarct.PhotosetDetailsRepositoryContract
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindPhotosetRepository(photosetRepository: PhotosetRepository): PhotosetRepositoryContract

    @Binds
    fun bindPhotosetDetailsRepository(photosetDetailsRepository: PhotosetDetailRepository): PhotosetDetailsRepositoryContract

}