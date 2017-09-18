package com.marekmacko.kotlinapp.api

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class NetworkModule {

    @Provides
    @Singleton
    fun provideWeatherService(): WeatherService = WeatherService.create()
}