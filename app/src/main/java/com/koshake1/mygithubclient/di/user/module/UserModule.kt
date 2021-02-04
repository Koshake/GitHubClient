package com.koshake1.mygithubclient.di.user.module

import com.koshake1.mygithubclient.di.user.UserScope
import com.koshake1.mygithubclient.mvp.model.api.IDataSource
import com.koshake1.mygithubclient.mvp.model.cache.IGithubUsersCache
import com.koshake1.mygithubclient.mvp.model.cache.room.RoomGithubUsersCache
import com.koshake1.mygithubclient.mvp.model.network.INetworkStatus
import com.koshake1.mygithubclient.mvp.model.repo.IGithubUsersRepo
import com.koshake1.mygithubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import com.koshake1.mygithubclient.mvp.model.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserModule {

    @UserScope
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubUsersCache) : IGithubUsersRepo =
        RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Provides
    fun usersCache(database: Database): IGithubUsersCache {
        return RoomGithubUsersCache(database)
    }
}