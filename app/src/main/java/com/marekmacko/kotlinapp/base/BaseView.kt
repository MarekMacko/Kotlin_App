package com.marekmacko.kotlinapp.base


interface BaseView<T> {
    fun setPresenter(presenter: T)

    fun showError(message: String)
}