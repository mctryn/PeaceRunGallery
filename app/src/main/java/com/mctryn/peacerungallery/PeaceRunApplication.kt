package com.mctryn.peacerungallery

import android.app.Application
import com.mctryn.peacerungallery.di.ApplicationComponent
import com.mctryn.peacerungallery.di.DaggerApplicationComponent
import dagger.android.*
import javax.inject.Inject

class PeaceRunApplication : Application() , HasAndroidInjector {
    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        val appComponent: ApplicationComponent = DaggerApplicationComponent.builder()
            .build()
        appComponent.inject(this)
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any?>

    override fun androidInjector(): AndroidInjector<Any?> {
        return androidInjector
    }
}