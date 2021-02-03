package com.koshake1.mygithubclient.di.modules

import androidx.room.Room
import com.koshake1.mygithubclient.App
import com.koshake1.mygithubclient.mvp.model.cache.IGithubRepositoriesCache
import com.koshake1.mygithubclient.mvp.model.cache.IGithubUsersCache
import com.koshake1.mygithubclient.mvp.model.cache.room.RoomGithubRepositoriesCache
import com.koshake1.mygithubclient.mvp.model.cache.room.RoomGithubUsersCache
import com.koshake1.mygithubclient.mvp.model.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Singleton
    @Provides
    fun usersCache(database: Database): IGithubUsersCache {
        return RoomGithubUsersCache(database)
    }

    @Singleton
    @Provides
    fun repositoriesCache(database: Database): IGithubRepositoriesCache {
        return RoomGithubRepositoriesCache(database)
    }

}