package com.marekmacko.kotlinapp

import android.app.Application
import android.support.annotation.VisibleForTesting


class App : Application() {

    @set:VisibleForTesting
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}
