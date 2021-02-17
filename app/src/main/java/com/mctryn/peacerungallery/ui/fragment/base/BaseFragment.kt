package com.mctryn.peacerungallery.ui.fragment.base

import android.content.Context
import android.widget.Toast
import com.mctryn.peacerungallery.presentation.view.BaseView
import com.mctryn.peacerungallery.utils.preloadWithGlide
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment

abstract class BaseFragment(fragmentResId: Int) : MvpAppCompatFragment(fragmentResId), BaseView {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onErrorOccurred(error: String) {
        Toast.makeText(this.context, error, Toast.LENGTH_LONG).show()
    }

    override fun preloadImage(link: String) {
        preloadWithGlide(link)
    }
}