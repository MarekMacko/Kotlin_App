package com.marekmacko.kotlinapp.repository

import com.marekmacko.kotlinapp.api.WeatherService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRemoteWeatherRepository(weatherService: WeatherService): RemoteWeatherRepository =
            RemoteWeatherRepository(weatherService)

    @Provides
    @Singleton
    fun provideWeatherRepository(remoteWeatherRepository: RemoteWeatherRepository): WeatherRepository =
            WeatherRepository(remoteWeatherRepository)
}