package com.marekmacko.kotlinapp

import com.marekmacko.kotlinapp.data.WeatherModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(WeatherModule::class))
interface WeatherComponent {

    fun inject(mainActivity: MainActivity)
}
