package com.mctryn.peacerungallery.utils

import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.mctryn.peacerungallery.R

fun ImageView.loadImageFromLink(imageLink: String) {
    Glide.with(context.applicationContext)
        .load(imageLink)
        .apply(
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(ColorDrawable(ContextCompat.getColor(this.context, R.color.placeholder_gray)))
                .error(ColorDrawable(ContextCompat.getColor(this.context, R.color.placeholder_gray)))
        )
        .into(this)
}

fun ImageView.loadDetailImageFromLink(imageLink: String) {
    Glide.with(context.applicationContext)
        .load(imageLink)
        .apply(
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(ColorDrawable(ContextCompat.getColor(this.context, R.color.placeholder_gray)))
                .error(ColorDrawable(ContextCompat.getColor(this.context, R.color.placeholder_gray)))
        )
        .into(this)
}