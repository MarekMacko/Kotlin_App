package com.marekmacko.kotlinapp.mvp

import com.marekmacko.kotlinapp.repository.WeatherRepository
import javax.inject.Inject


class WeatherPresenter @Inject constructor(private val view: WeatherMvp.View,
                                           private val weatherRepository: WeatherRepository)
    : WeatherMvp.Presenter {

    override fun fetchForecast() = // TODO: add disposable
            weatherRepository.getWeeklyForecast(
                    { view.updateWeeklyForecast(it) },
                    { view.showError(it) }
            )
}