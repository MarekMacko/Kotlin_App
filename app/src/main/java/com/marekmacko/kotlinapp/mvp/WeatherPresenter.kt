package com.marekmacko.kotlinapp.mvp

import com.marekmacko.kotlinapp.repository.WeatherRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class WeatherPresenter @Inject constructor(private val view: WeatherMvp.View,
                                           private val weatherRepository: WeatherRepository)
    : WeatherMvp.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun fetchForecast() {
        view.showLoading()
        val disposable = weatherRepository.getWeeklyForecast(
                {
                    view.hideLoading()
                    view.updateWeeklyForecast(it)
                },
                {
                    view.hideLoading()
                    view.showError(it)
                }
        )
        compositeDisposable.add(disposable)
    }

    override fun cancelFetch() {
        compositeDisposable.clear()
    }
}