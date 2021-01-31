package com.koshake1.mygithubclient.mvp.presenter

import com.koshake1.mygithubclient.mvp.model.GithubRepository
import com.koshake1.mygithubclient.mvp.view.list.IRepositoryView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class RepositoryPresenter(private val githubRepository: GithubRepository, private val router: Router) : MvpPresenter<IRepositoryView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setId(githubRepository.id)
        viewState.setTitle(githubRepository.name ?: "")
        viewState.setForksCount((githubRepository.forksCount ?: 0).toString())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        System.out.println("onDestroy presenter")
    }
}