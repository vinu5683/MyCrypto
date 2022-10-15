package com.juno.mycrypto.utils

import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.juno.mycrypto.R

object PhotoUtils {

    fun ImageView.loadUrl(url: String) {

        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(this@loadUrl.context)) }
            .build()

        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(500)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .data(url)
            .target(this)
            .build()

        imageLoader.enqueue(request)
    }

}
