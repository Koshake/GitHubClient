package com.koshake1.mygithubclient.mvp.model.cache.room

import com.koshake1.mygithubclient.mvp.model.GithubRepository
import com.koshake1.mygithubclient.mvp.model.GithubUser
import com.koshake1.mygithubclient.mvp.model.cache.IGithubRepositoriesCache
import com.koshake1.mygithubclient.mvp.model.room.Database
import com.koshake1.mygithubclient.mvp.model.room.RoomGithubRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RoomGithubRepositoriesCache(private val db: Database) : IGithubRepositoriesCache {

    override fun getRepositoriesFromCache(user: GithubUser): Single<List<GithubRepository>> =
        Single.fromCallable {
            val roomUser = db.userDao.findByLogin(user.login)
            return@fromCallable db.repositoryDao.findForUser(roomUser.id)
                .map { GithubRepository(it.id, it.name, it.forksCount) }
        }.subscribeOn(Schedulers.io())

    override fun saveRepositoriesToCache(user: GithubUser, repositories: List<GithubRepository>) =
        Completable.fromCallable {
            val roomUser = user.login?.let { db.userDao.findByLogin(it) }
            db.repositoryDao.insert(
               db.repositoryDao.findForUser(roomUser.id)
               .map { RoomGithubRepository(it.id, it.name, it.forksCount, roomUser.id)})
        }.subscribeOn(Schedulers.io())
}