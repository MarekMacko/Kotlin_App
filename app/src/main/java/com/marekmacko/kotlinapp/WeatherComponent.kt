package com.marekmacko.kotlinapp

import com.marekmacko.kotlinapp.api.NetworkModule
import com.marekmacko.kotlinapp.repository.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class, RepositoryModule::class))
interface WeatherComponent {

    fun inject(weeklyForecastFragment: WeeklyForecastFragment)
}
