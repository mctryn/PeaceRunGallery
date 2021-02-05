package com.mctryn.peacerungallery.di

import com.mctryn.peacerungallery.PeaceRunApplication
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class])
@Singleton
interface ApplicationComponent {
    fun inject(application: PeaceRunApplication)
}