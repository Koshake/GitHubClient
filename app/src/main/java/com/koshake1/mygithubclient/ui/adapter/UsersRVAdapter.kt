package com.koshake1.mygithubclient.mvp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.koshake1.mygithubclient.R
import com.koshake1.mygithubclient.mvp.model.image.IImageLoader
import com.koshake1.mygithubclient.mvp.presenter.list.IUserListPresenter
import com.koshake1.mygithubclient.mvp.view.list.IUserItemView
import com.koshake1.mygithubclient.ui.GlideImageLoader
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.view.*
import javax.inject.Inject

class UsersRVAdapter(
    private val presenter: IUserListPresenter,
    private val imageLoader: GlideImageLoader
) : RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer, IUserItemView {

        override fun setLogin(text: String) = with(containerView) {
            tv_login.text = text
        }

        override fun loadAvatar(url: String) = with(containerView) {
            imageLoader.loadInto(url, iv_avatar)
        }

        override var pos = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()
}