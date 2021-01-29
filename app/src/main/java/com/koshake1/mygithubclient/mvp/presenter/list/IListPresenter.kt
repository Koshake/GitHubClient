package com.koshake1.mygithubclient.mvp.presenter.list

import com.koshake1.mygithubclient.mvp.view.list.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}