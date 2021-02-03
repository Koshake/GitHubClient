package com.koshake1.mygithubclient.di

import com.koshake1.mygithubclient.di.modules.*
import com.koshake1.mygithubclient.mvp.presenter.MainPresenter
import com.koshake1.mygithubclient.mvp.presenter.RepositoryPresenter
import com.koshake1.mygithubclient.mvp.presenter.UserPresenter
import com.koshake1.mygithubclient.mvp.presenter.UsersPresenter
import com.koshake1.mygithubclient.mvp.ui.adapter.UsersRVAdapter
import com.koshake1.mygithubclient.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        DatabaseModule::class,
        CiceroneModule::class,
        RepoModule::class,
        ImageModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
    fun inject(adapter: UsersRVAdapter)
}