package com.marekmacko.kotlinapp.data

import com.marekmacko.kotlinapp.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class WeatherModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(): WeatherRepository =
            WeatherRepository()
}