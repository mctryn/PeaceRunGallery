package com.mctryn.peacerungallery.ui.fragment.base

import android.content.Context
import android.widget.Toast
import com.mctryn.peacerungallery.R
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
        val connectionError = getString(R.string.connection_error)
        Toast.makeText(this.context, connectionError, Toast.LENGTH_LONG).show()
        hideLoadingIndicator()
    }

    override fun preloadImage(link: String) {
        preloadWithGlide(link)
    }

    abstract fun hideLoadingIndicator()
    abstract fun onRefresh()
}