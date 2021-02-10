package com.mctryn.peacerungallery.utils

import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.mctryn.peacerungallery.R

fun ImageView.loadImageFromLink(imageLink: String) {
    val circularProgressDrawable = initCircularProgressDrawable(this)

    Glide.with(context.applicationContext)
        .load(imageLink)
        .apply(
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(circularProgressDrawable)
                .error(
                    ColorDrawable(
                        ContextCompat.getColor(
                            this.context,
                            R.color.placeholder_gray
                        )
                    )
                )
        )
        .into(this)
}

fun ImageView.loadDetailImageFromLink(imageLink: String) {
    val circularProgressDrawable = initCircularProgressDrawable(this)

    Glide.with(context.applicationContext)
        .load(imageLink)
        .apply(
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(circularProgressDrawable)
                .error(
                    ColorDrawable(
                        ContextCompat.getColor(
                            this.context,
                            R.color.placeholder_gray
                        )
                    )
                )
        )
        .into(this)
}

fun initCircularProgressDrawable(view: ImageView): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(view.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    return circularProgressDrawable
}