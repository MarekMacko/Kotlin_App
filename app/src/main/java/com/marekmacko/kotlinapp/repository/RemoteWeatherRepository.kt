package com.marekmacko.kotlinapp.repository

import com.marekmacko.kotlinapp.api.WeatherService
import com.marekmacko.kotlinapp.base.DataCallback
import com.marekmacko.kotlinapp.data.WeeklyForecast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RemoteWeatherRepository(private val weatherService: WeatherService) {

    private val API_ZIP_CODE = "94043"

    fun getWeeklyForecast(dataCallback: DataCallback<WeeklyForecast>) {
        weatherService.getWeeklyForecast(API_ZIP_CODE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { weeklyForecast -> dataCallback.onDataLoaded(weeklyForecast) },
                        { error -> dataCallback.onError(error.message ?: "No message provided") }
                )
    }
}