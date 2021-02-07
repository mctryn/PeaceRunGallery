package com.mctryn.peacerungallery.di

import com.mctryn.peacerungallery.di.network.NetworkModule
import com.mctryn.peacerungallery.di.network.RepositoryModule
import dagger.Module

@Module(includes = [FragmentModule::class, NetworkModule::class, RepositoryModule::class])
class ApplicationModule
