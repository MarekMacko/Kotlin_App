package com.marekmacko.kotlinapp.repository

import com.marekmacko.kotlinapp.api.WeatherService
import com.marekmacko.kotlinapp.data.WeeklyForecast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RemoteWeatherRepository(private val weatherService: WeatherService) {

    companion object {
        private const val API_ZIP_CODE = "94043"
    }

    fun getWeeklyForecast(onLoad: (WeeklyForecast) -> Unit, onError: (String) -> Unit) {
        weatherService.getWeeklyForecast(API_ZIP_CODE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onLoad(it) },
                        { onError(it.message ?: "No message provided") }
                )
    }
}