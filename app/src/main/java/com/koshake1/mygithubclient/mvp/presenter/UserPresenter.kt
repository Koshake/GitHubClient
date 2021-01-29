package com.koshake1.mygithubclient.mvp.presenter

import com.koshake1.mygithubclient.mvp.model.GithubRepository
import com.koshake1.mygithubclient.mvp.model.GithubUser
import com.koshake1.mygithubclient.mvp.model.repo.IGithubRepositoriesRepo
import com.koshake1.mygithubclient.mvp.model.repo.IGithubUsersRepo
import com.koshake1.mygithubclient.mvp.presenter.list.IListPresenter
import com.koshake1.mygithubclient.mvp.presenter.list.IRepoListPresenter
import com.koshake1.mygithubclient.mvp.presenter.list.IUserListPresenter
import com.koshake1.mygithubclient.mvp.view.IUserView
import com.koshake1.mygithubclient.mvp.view.list.IRepoItemView
import com.koshake1.mygithubclient.mvp.view.list.IUserItemView
import com.koshake1.mygithubclient.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(
    private val mainThreadScheduler: Scheduler,
    private val usersRepo: IGithubRepositoriesRepo,
    private val router: Router) : MvpPresenter<IUserView>() {

    class ReposListPresenter : IRepoListPresenter {
        val repos = mutableListOf<GithubRepository>()

        override var itemClickListener: ((IRepoItemView) -> Unit)? = null

        override fun bindView(view: IRepoItemView) {
            val repo = repos[view.pos]
            repo.name?.let {view.setName(it)}
        }

        override fun getCount(): Int {
            return repos.size
        }
    }

    val reposListPresenter = ReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        reposListPresenter.itemClickListener = { itemView ->
            val repo = reposListPresenter.repos[itemView.pos]
            router.navigateTo(Screens.RepositoryScreen(repo))
        }
    }

    fun setUser(user: GithubUser) {
        viewState.setTitle(user.login)
        loadData(user)
    }

    private fun loadData(user : GithubUser) {
        usersRepo.getRepositories(user)
            .observeOn(mainThreadScheduler)
            .subscribe({ repos ->
                reposListPresenter.repos.clear()
                reposListPresenter.repos.addAll(repos)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}