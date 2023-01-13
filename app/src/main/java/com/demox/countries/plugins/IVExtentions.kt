package com.demox.countries.plugins

import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.demox.countries.R

fun ImageView.loadUrl(url: String) {
    val imageLoader = ImageLoader.Builder(this.context)
        .componentRegistry { add(SvgDecoder(this@loadUrl.context)) }
        .build()

    val request = ImageRequest.Builder(this.context)
        .placeholder(R.drawable.load_imege)
        .error(R.drawable.load_imege)
        .data(url)
        .target(this)
        .build()

    imageLoader.enqueue(request)
}
