package com.koshake1.mygithubclient.di.repository.module

import com.koshake1.mygithubclient.di.repository.RepositoryScope
import com.koshake1.mygithubclient.mvp.model.api.IDataSource
import com.koshake1.mygithubclient.mvp.model.cache.IGithubRepositoriesCache
import com.koshake1.mygithubclient.mvp.model.cache.room.RoomGithubRepositoriesCache
import com.koshake1.mygithubclient.mvp.model.network.INetworkStatus
import com.koshake1.mygithubclient.mvp.model.repo.IGithubRepositoriesRepo
import com.koshake1.mygithubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import com.koshake1.mygithubclient.mvp.model.room.Database
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @RepositoryScope
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubRepositoriesCache) : IGithubRepositoriesRepo =
        RetrofitGithubRepositoriesRepo(api, networkStatus, cache)

    @Provides
    fun repositoriesCache(database: Database): IGithubRepositoriesCache {
        return RoomGithubRepositoriesCache(database)
    }
}