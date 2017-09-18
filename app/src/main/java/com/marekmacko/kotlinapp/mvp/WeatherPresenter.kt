package com.marekmacko.kotlinapp.mvp

import com.marekmacko.kotlinapp.repository.WeatherRepository


class WeatherPresenter(private val view: WeatherMvp.View,
                       private val weatherRepository: WeatherRepository) : WeatherMvp.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun fetchForecast() = // TODO: add disposable
            weatherRepository.getWeeklyForecast(
                    { view.updateWeeklyForecast(it) },
                    { view.showError(it) }
            )
}