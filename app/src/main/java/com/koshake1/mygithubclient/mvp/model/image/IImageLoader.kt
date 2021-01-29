package com.koshake1.mygithubclient.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}