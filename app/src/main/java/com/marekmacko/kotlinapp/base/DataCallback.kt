package com.marekmacko.kotlinapp.base


interface DataCallback<in T> {
    fun onDataLoaded(data: T)
    fun onError(errorMessage: String)
}