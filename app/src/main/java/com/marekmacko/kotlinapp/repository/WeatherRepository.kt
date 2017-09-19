package com.marekmacko.kotlinapp.repository

import com.marekmacko.kotlinapp.data.WeeklyForecast
import io.reactivex.disposables.Disposable


class WeatherRepository(private val remoteWeatherRepository: RemoteWeatherRepository) {

    fun getWeeklyForecast(onLoad: (WeeklyForecast) -> Unit, onError: (String) -> Unit): Disposable =
            remoteWeatherRepository.getWeeklyForecast(onLoad, onError)
}