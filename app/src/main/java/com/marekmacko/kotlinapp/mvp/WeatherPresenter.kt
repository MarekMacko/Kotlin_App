package com.marekmacko.kotlinapp.mvp

import com.marekmacko.kotlinapp.repository.WeatherRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class WeatherPresenter @Inject constructor(private val view: WeatherMvp.View,
                                           private val weatherRepository: WeatherRepository)
    : WeatherMvp.Presenter {
    private val compositeDisposable = CompositeDisposable()

    override fun fetchForecast() {
        val disposable = weatherRepository.getWeeklyForecast(
                { view.updateWeeklyForecast(it) },
                { view.showError(it) }
        )
        compositeDisposable.add(disposable)
    }
}