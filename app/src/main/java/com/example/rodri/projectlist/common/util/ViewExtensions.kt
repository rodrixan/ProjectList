package com.example.rodri.projectlist.common.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rodri.projectlist.R

fun ImageView.loadFromDrawable(drawableId: Int) {
    if (drawableId != -1) {
        Glide.with(context).load(drawableId).apply(RequestOptions().centerInside()).into(this)
    }
}

fun ImageView.loadFromUrl(url: String?, resizeSize: Pair<Int, Int>? = null) {
    url?.let {
        val glideRequest = Glide.with(context).load(it)
        if (resizeSize != null) {
            glideRequest.apply(RequestOptions().override(resizeSize.first, resizeSize.second))
        }
        glideRequest.into(this)
    } ?: loadFromDrawable(R.drawable.ic_img_placeholder)
}