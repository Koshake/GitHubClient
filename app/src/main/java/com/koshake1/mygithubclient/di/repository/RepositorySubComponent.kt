package com.koshake1.mygithubclient.di.repository

import com.koshake1.mygithubclient.di.repository.module.RepositoryModule
import com.koshake1.mygithubclient.mvp.presenter.RepositoryPresenter
import com.koshake1.mygithubclient.mvp.presenter.UserPresenter
import dagger.Subcomponent

@RepositoryScope
@Subcomponent(
    modules = [
        RepositoryModule::class
    ]
)
interface RepositorySubComponent {
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
}