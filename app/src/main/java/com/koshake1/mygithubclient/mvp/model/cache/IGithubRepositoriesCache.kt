package com.koshake1.mygithubclient.mvp.model.cache

import com.koshake1.mygithubclient.mvp.model.GithubRepository
import com.koshake1.mygithubclient.mvp.model.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesCache {
    fun getRepositoriesFromCache(user : GithubUser) : Single<List<GithubRepository>>
    fun saveRepositoriesToCache(user : GithubUser, repositories : List<GithubRepository>) : Completable
}