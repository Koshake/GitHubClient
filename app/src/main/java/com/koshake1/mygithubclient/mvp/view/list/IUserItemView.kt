package com.koshake1.mygithubclient.mvp.view.list

interface IUserItemView : IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}