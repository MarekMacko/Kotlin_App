package com.marekmacko.kotlinapp.repository

import com.marekmacko.kotlinapp.api.WeatherService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherService: WeatherService): WeatherRepository =
            WeatherRepository(weatherService)
}
