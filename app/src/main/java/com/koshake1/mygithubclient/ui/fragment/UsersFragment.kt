package com.koshake1.mygithubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.koshake1.mygithubclient.ApiHolder
import com.koshake1.mygithubclient.App
import com.koshake1.mygithubclient.R
import com.koshake1.mygithubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import com.koshake1.mygithubclient.mvp.model.room.Database
import com.koshake1.mygithubclient.mvp.presenter.UsersPresenter
import com.koshake1.mygithubclient.mvp.ui.adapter.UsersRVAdapter
import com.koshake1.mygithubclient.mvp.view.IUsersView
import com.koshake1.mygithubclient.mvp.model.network.AndroidNetworkStatus
import com.koshake1.mygithubclient.ui.BackButtonListener
import com.koshake1.mygithubclient.ui.GlideImageLoader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), IUsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepo(ApiHolder().api, AndroidNetworkStatus(App.instance), Database.getInstance()),
            App.instance.router
        )
    }
    private var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.fragment_users, null)


    override fun init() {
        rv_users.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        rv_users.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}