package com.koshake1.mygithubclient.di.user

import com.koshake1.mygithubclient.di.repository.RepositorySubComponent
import com.koshake1.mygithubclient.di.user.module.UserModule
import com.koshake1.mygithubclient.mvp.presenter.UsersPresenter
import com.koshake1.mygithubclient.ui.adapter.UsersRVAdapter
import dagger.Subcomponent

@UserScope
@Subcomponent(
    modules = [
        UserModule::class
    ]
)
interface UserSubComponent {
    fun repositorySubComponent() : RepositorySubComponent
    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
}