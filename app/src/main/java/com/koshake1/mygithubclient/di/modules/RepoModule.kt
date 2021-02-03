package com.koshake1.mygithubclient.di.modules

import com.koshake1.mygithubclient.mvp.model.api.IDataSource
import com.koshake1.mygithubclient.mvp.model.cache.IGithubRepositoriesCache
import com.koshake1.mygithubclient.mvp.model.cache.IGithubUsersCache
import com.koshake1.mygithubclient.mvp.model.network.INetworkStatus
import com.koshake1.mygithubclient.mvp.model.repo.IGithubRepositoriesRepo
import com.koshake1.mygithubclient.mvp.model.repo.IGithubUsersRepo
import com.koshake1.mygithubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import com.koshake1.mygithubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubUsersCache) : IGithubUsersRepo =
    RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubRepositoriesCache) : IGithubRepositoriesRepo =
        RetrofitGithubRepositoriesRepo(api, networkStatus, cache)

}