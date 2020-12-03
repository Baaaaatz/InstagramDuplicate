package com.batzalcancia.core.helpers

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import coil.request.CachePolicy
import com.batzalcancia.core.R

/**
 * @param url Url to be loaded on the imageView
 */
fun ImageView.loadImageFromUrl(
    url: String
) = load(url) {
    diskCachePolicy(CachePolicy.ENABLED)
    memoryCachePolicy(CachePolicy.ENABLED)
    placeholder(CircularProgressDrawable(context).apply {
        setStyle(CircularProgressDrawable.DEFAULT)
    })
    error(R.drawable.img_error)
    crossfade(true)
}