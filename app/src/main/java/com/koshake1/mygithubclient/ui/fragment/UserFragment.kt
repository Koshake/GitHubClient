package com.koshake1.mygithubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.koshake1.mygithubclient.ApiHolder
import com.koshake1.mygithubclient.App
import com.koshake1.mygithubclient.R
import com.koshake1.mygithubclient.mvp.model.GithubUser
import com.koshake1.mygithubclient.mvp.model.cache.room.RoomGithubRepositoriesCache
import com.koshake1.mygithubclient.mvp.model.network.AndroidNetworkStatus
import com.koshake1.mygithubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import com.koshake1.mygithubclient.mvp.model.room.Database
import com.koshake1.mygithubclient.mvp.presenter.UserPresenter
import com.koshake1.mygithubclient.mvp.ui.adapter.UserAdapter
import com.koshake1.mygithubclient.mvp.view.IUserView
import com.koshake1.mygithubclient.ui.BackButtonListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment() : MvpAppCompatFragment(), IUserView,
    BackButtonListener {
    companion object {
        private const val USER_ARG = "user"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    private var adapter: UserAdapter? = null

    private val userPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserPresenter(user).apply { App.instance.appComponent.inject(this) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.fragment_user, null)

    override fun backPressed() = userPresenter.backPressed()

    override fun init() {
        mainRecycler.layoutManager = LinearLayoutManager(context)
        adapter = UserAdapter(userPresenter.reposListPresenter)
        mainRecycler.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
}