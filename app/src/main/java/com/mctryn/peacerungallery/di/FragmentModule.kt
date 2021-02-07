package com.mctryn.peacerungallery.di

import com.mctryn.peacerungallery.ui.fragment.DetailListFragment
import com.mctryn.peacerungallery.ui.fragment.PhotosetListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun contributePhotosetListFragment(): PhotosetListFragment

    @ContributesAndroidInjector
    fun contributeDetailListFragment(): DetailListFragment

}