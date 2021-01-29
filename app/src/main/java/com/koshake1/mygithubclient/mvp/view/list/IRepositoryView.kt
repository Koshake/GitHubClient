package com.koshake1.mygithubclient.mvp.view.list

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IRepositoryView : MvpView {
    fun init()
    fun setId(id: String)
    fun setTitle(text: String)
    fun setForksCount(text: String)
}