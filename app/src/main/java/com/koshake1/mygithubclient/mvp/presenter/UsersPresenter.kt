package com.koshake1.mygithubclient.mvp.presenter

import com.koshake1.mygithubclient.mvp.model.GithubUser
import com.koshake1.mygithubclient.mvp.model.repo.IGithubUsersRepo
import com.koshake1.mygithubclient.mvp.model.room.Database
import com.koshake1.mygithubclient.mvp.presenter.list.IUserListPresenter
import com.koshake1.mygithubclient.mvp.view.IUsersView
import com.koshake1.mygithubclient.mvp.view.list.IUserItemView
import com.koshake1.mygithubclient.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UsersPresenter(
    private val mainThreadScheduler: Scheduler,

) : MvpPresenter<IUsersView>() {

    @Inject
    lateinit var usersRepo: IGithubUsersRepo
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var database: Database

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            user.login?.let {view.setLogin(it)}
            user.avatarUrl?.let {view.loadAvatar(it)}
        }

        override fun getCount(): Int {
            return users.size
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(Screens.UserScreen(user))
        }
    }

    val usersListPresenter = UsersListPresenter()
    private fun loadData() {
        usersRepo.getUsers()
            .observeOn(mainThreadScheduler)
            .subscribe({ users ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(users)
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