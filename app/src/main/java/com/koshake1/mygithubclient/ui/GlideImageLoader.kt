package com.koshake1.mygithubclient.ui

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.koshake1.mygithubclient.mvp.model.image.IImageLoader

class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .into(container)
    }
}