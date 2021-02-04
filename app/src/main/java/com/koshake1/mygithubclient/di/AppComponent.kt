package com.koshake1.mygithubclient.di

import com.koshake1.mygithubclient.di.modules.*
import com.koshake1.mygithubclient.di.user.UserSubComponent
import com.koshake1.mygithubclient.mvp.presenter.MainPresenter
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
        ImageModule::class
    ]
)
interface AppComponent {
    fun userSubComponent() : UserSubComponent
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    //fun inject(usersPresenter: UsersPresenter)
    //fun inject(userPresenter: UserPresenter)
    //fun inject(repositoryPresenter: RepositoryPresenter)
    //fun inject(adapter: UsersRVAdapter)
}