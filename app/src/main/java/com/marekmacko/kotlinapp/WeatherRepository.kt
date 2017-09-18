package com.marekmacko.kotlinapp

import com.marekmacko.kotlinapp.api.WeatherService
import com.marekmacko.kotlinapp.base.DataCallback
import com.marekmacko.kotlinapp.data.WeeklyForecast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class WeatherRepository {

    private val API_ZIP_CODE = "94043"

    private val weatherService by lazy {
        WeatherService.create()
    }

    fun getWeeklyForecast(dataCallback: DataCallback<WeeklyForecast>) {
        weatherService.getWeeklyForecast(API_ZIP_CODE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { weeklyForecast -> dataCallback.onDataLoaded(weeklyForecast) },
                        { error -> dataCallback.onError(error.message?: "No message provided") }
                )
    }
}