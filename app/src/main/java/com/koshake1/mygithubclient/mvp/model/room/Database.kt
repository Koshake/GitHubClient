package com.koshake1.mygithubclient.mvp.model.room

import androidx.room.RoomDatabase
import com.koshake1.mygithubclient.mvp.model.room.dao.RepositoryDao
import com.koshake1.mygithubclient.mvp.model.room.dao.UserDao

@androidx.room.Database(entities = [RoomGithubUser::class, RoomGithubRepository::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        const val DB_NAME = "database.db"
    }

}