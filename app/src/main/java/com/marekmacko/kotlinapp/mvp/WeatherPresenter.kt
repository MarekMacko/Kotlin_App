package com.marekmacko.kotlinapp.mvp

import com.marekmacko.kotlinapp.WeatherRepository
import com.marekmacko.kotlinapp.base.DataCallback
import com.marekmacko.kotlinapp.data.WeeklyForecast


class WeatherPresenter(private val view: WeatherMvp.View,
                       private val weatherRepository: WeatherRepository) : WeatherMvp.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun fetchForecast() { // TODO: lambda
        // TODO: add disposable
        weatherRepository.getWeeklyForecast(object : DataCallback<WeeklyForecast> {
            override fun onDataLoaded(data: WeeklyForecast) {
                view.updateWeeklyForecast(data)
            }

            override fun onError(errorMessage: String) {
                view.showError(errorMessage)
            }
        })
    }
}