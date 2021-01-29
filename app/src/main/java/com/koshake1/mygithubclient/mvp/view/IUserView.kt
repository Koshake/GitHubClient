package com.koshake1.mygithubclient.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IUserView : MvpView {
    fun setTitle(text: String)
    fun updateList()
    fun init()
}