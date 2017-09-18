package com.marekmacko.kotlinapp.repository

import com.marekmacko.kotlinapp.data.WeeklyForecast


class WeatherRepository(private val remoteWeatherRepository: RemoteWeatherRepository) {

    fun getWeeklyForecast(onLoad: (WeeklyForecast) -> Unit, onError: (String) -> Unit) =
            remoteWeatherRepository.getWeeklyForecast(onLoad, onError)
}