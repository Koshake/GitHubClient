package com.koshake1.mygithubclient.mvp.model.cache

import com.koshake1.mygithubclient.mvp.model.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubUsersCache {
    fun getUsersFromCache() : Single<List<GithubUser>>
    fun saveUsersToCache(user : List<GithubUser>) : Completable
}