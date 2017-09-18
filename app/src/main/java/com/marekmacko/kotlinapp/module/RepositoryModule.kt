package com.marekmacko.kotlinapp.module

import com.marekmacko.kotlinapp.repository.RemoteWeatherRepository
import com.marekmacko.kotlinapp.repository.WeatherRepository
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