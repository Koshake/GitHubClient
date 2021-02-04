package com.koshake1.mygithubclient.di.modules

import android.widget.ImageView
import com.koshake1.mygithubclient.mvp.model.image.IImageLoader
import com.koshake1.mygithubclient.ui.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Singleton
    @Provides
    fun imageLoader() : IImageLoader<ImageView> =  GlideImageLoader()
}