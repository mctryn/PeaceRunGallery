package com.mctryn.peacerungallery.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImageFromLink(imageLink: String) {
    Glide.with(context.applicationContext)
        .load(imageLink)
        .apply(
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .centerCrop()
        )
        .into(this)
}

fun ImageView.loadDetailImageFromLink(imageLink: String) {
    Glide.with(context.applicationContext)
        .load(imageLink)
        .apply(
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        )
        .into(this)
}