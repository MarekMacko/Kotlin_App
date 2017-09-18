package com.marekmacko.kotlinapp.repository

import com.marekmacko.kotlinapp.base.DataCallback
import com.marekmacko.kotlinapp.data.WeeklyForecast


class WeatherRepository(private val remoteWeatherRepository: RemoteWeatherRepository) {

    fun getWeeklyForecast(dataCallback: DataCallback<WeeklyForecast>) =
            remoteWeatherRepository.getWeeklyForecast(dataCallback)
}