package com.marekmacko.kotlinapp

import com.marekmacko.kotlinapp.MainActivity
import com.marekmacko.kotlinapp.api.NetworkModule
import com.marekmacko.kotlinapp.module.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class, RepositoryModule::class))
interface WeatherComponent {

    fun inject(mainActivity: MainActivity)
}
