package com.marekmacko.kotlinapp.repository

import com.marekmacko.kotlinapp.api.WeatherService
import com.marekmacko.kotlinapp.data.WeeklyForecast
import io.reactivex.Observable


class RemoteWeatherRepository(private val weatherService: WeatherService) {

    companion object {
        private const val API_ZIP_CODE = "94043"
    }

    fun getWeeklyForecast(): Observable<WeeklyForecast> =
            weatherService.getWeeklyForecast(API_ZIP_CODE)
}
