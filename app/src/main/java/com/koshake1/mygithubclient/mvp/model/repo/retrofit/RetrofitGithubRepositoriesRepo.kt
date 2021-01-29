package com.koshake1.mygithubclient.mvp.model.repo.retrofit

import com.koshake1.mygithubclient.mvp.model.GithubRepository
import com.koshake1.mygithubclient.mvp.model.GithubUser
import com.koshake1.mygithubclient.mvp.model.api.IDataSource
import com.koshake1.mygithubclient.mvp.model.network.INetworkStatus
import com.koshake1.mygithubclient.mvp.model.repo.IGithubRepositoriesRepo
import com.koshake1.mygithubclient.mvp.model.room.Database
import com.koshake1.mygithubclient.mvp.model.room.RoomGithubRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val db: Database
) :
    IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getRepos(url).flatMap { repositories ->
                        Single.fromCallable {
                            val roomUser = user.login?.let { db.userDao.findByLogin(it) }
                                ?: throw RuntimeException("No such user in cache")
                            val roomRepos = repositories.map {
                                RoomGithubRepository(
                                    it.id ?: "",
                                    it.name ?: "",
                                    it.forksCount ?: 0,
                                    roomUser.id
                                )
                            }

                            db.repositoryDao.insert(roomRepos)

                            repositories
                        }
                    }
                }
            } else {
                Single.fromCallable {
                    val roomUser = user.login?.let { db.userDao.findByLogin(it) }
                        ?: throw RuntimeException("No such user in cache")
                    db.repositoryDao.findForUser(roomUser.id)
                        .map { GithubRepository(it.id, it.name, it.forksCount) }
                }
            }
        }.subscribeOn(Schedulers.io())
}