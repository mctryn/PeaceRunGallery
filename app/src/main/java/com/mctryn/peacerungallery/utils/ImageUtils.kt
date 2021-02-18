package com.mctryn.peacerungallery.utils

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.mctryn.peacerungallery.R

fun ImageView.loadImageFromLink(imageLink: String) {
    val placeholderItem = initPlaceholder(context)
    Glide.with(context.applicationContext)
        .load(imageLink)
        .apply(
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(placeholderItem)
                .error(placeholderItem)
        )
        .into(this)
}

fun ImageView.loadDetailImageFromLink(imageLink: String) {
    val placeholderItem = initPlaceholder(context)
    Glide.with(context.applicationContext)
        .load(imageLink)
        .apply(
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholderItem)
                .error(placeholderItem)
        )
        .into(this)
}

fun initPlaceholder(context: Context): ColorDrawable {
    return ColorDrawable(ContextCompat.getColor(context, R.color.placeholder_gray))
}

fun Fragment.preloadWithGlide(imageLink: String) {
    context?.let {
        Glide.with(it)
            .load(imageLink)
            .apply(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            )
            .preload()
    }
}