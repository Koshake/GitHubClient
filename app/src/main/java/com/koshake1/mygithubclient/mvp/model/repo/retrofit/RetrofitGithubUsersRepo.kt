package com.koshake1.mygithubclient.mvp.model.repo.retrofit

import com.koshake1.mygithubclient.mvp.model.GithubUser
import com.koshake1.mygithubclient.mvp.model.api.IDataSource
import com.koshake1.mygithubclient.mvp.model.cache.IGithubUsersCache
import com.koshake1.mygithubclient.mvp.model.cache.room.RoomGithubUsersCache
import com.koshake1.mygithubclient.mvp.model.network.INetworkStatus
import com.koshake1.mygithubclient.mvp.model.repo.IGithubUsersRepo
import com.koshake1.mygithubclient.mvp.model.room.Database
import com.koshake1.mygithubclient.mvp.model.room.RoomGithubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val userCache: IGithubUsersCache
) : IGithubUsersRepo {

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                Single.fromCallable {
                    userCache.saveUsersToCache(users)
                    users
                }
            }
        } else {
            userCache.getUsersFromCache()
        }
    }.subscribeOn(Schedulers.io())
}