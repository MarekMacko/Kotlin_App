package com.marekmacko.kotlinapp.base


interface BaseView<in T : BasePresenter> {
    fun setPresenter(presenter: T)

    fun showError(message: String)
}