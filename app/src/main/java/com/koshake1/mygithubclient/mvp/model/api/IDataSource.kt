package com.koshake1.mygithubclient.mvp.model.api

import com.koshake1.mygithubclient.mvp.model.GithubRepository
import com.koshake1.mygithubclient.mvp.model.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {
    @GET("/users")
    fun getUsers() : Single<List<GithubUser>>

    @GET
    fun getRepos(@Url url : String) : Single<List<GithubRepository>>
}