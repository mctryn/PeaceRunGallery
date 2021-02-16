package com.mctryn.peacerungallery.ui.fragment.base

import android.content.Context
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.mctryn.peacerungallery.presentation.view.BaseView
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
}