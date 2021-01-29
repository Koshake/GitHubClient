package com.koshake1.mygithubclient.mvp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.koshake1.mygithubclient.R
import com.koshake1.mygithubclient.mvp.presenter.list.IRepoListPresenter
import com.koshake1.mygithubclient.mvp.view.list.IRepoItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repo.view.*


class UserAdapter(
    private val presenter: IRepoListPresenter,
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer, IRepoItemView {

        override fun setName(text: String) = with(containerView) { tv_repo.text = text }

        override var pos = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false))

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()
}