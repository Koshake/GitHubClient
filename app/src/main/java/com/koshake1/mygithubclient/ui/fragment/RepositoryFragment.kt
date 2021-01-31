package com.koshake1.mygithubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.koshake1.mygithubclient.App
import com.koshake1.mygithubclient.R
import com.koshake1.mygithubclient.mvp.model.GithubRepository
import com.koshake1.mygithubclient.mvp.presenter.RepositoryPresenter
import com.koshake1.mygithubclient.mvp.view.list.IRepositoryView
import com.koshake1.mygithubclient.ui.BackButtonListener
import kotlinx.android.synthetic.main.fragment_repository.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoryFragment : MvpAppCompatFragment(), IRepositoryView,
    BackButtonListener {
    companion object {
        private const val REPOSITORY_ARG = "repository"

        fun newInstance(repository: GithubRepository) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_ARG, repository)
            }
        }
    }

    val presenter: RepositoryPresenter by moxyPresenter {
        val repository = arguments?.getParcelable<GithubRepository>(REPOSITORY_ARG) as GithubRepository

        RepositoryPresenter(repository, App.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.fragment_repository, null)

    override fun init() {
    }

    override fun setId(id: String) {
        tv_repo_id.text = "Id: $id"
    }

    override fun setTitle(text: String) {
        tv_user_repo.text = "Title: $text"
    }

    override fun setForksCount(text: String) {
        tv_forks_count.text = "Forks count: $text"
    }

    override fun backPressed() = presenter.backPressed()

    override fun onDestroy() {
        super.onDestroy()
        System.out.println("onDestroy")
    }
}