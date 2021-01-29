package com.koshake1.mygithubclient.mvp.presenter

import com.koshake1.mygithubclient.mvp.model.GithubUser
import com.koshake1.mygithubclient.mvp.presenter.list.IUserListPresenter
import com.koshake1.mygithubclient.mvp.view.MainView
import com.koshake1.mygithubclient.mvp.view.list.IUserItemView
import com.koshake1.mygithubclient.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClicked() {
        router.exit()
    }
}