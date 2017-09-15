package com.marekmacko.kotlinapp.mvp

import com.marekmacko.kotlinapp.api.WeatherService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class WeatherPresenter(private val view: WeatherMvp.View) : WeatherMvp.Presenter {

    private val API_ZIP_CODE = "94043"

    private val weatherService by lazy {
        WeatherService.create()
    }

    init {
        view.setPresenter(this)
    }

    override fun fetchForecast() {
        // TODO: add disposable
        weatherService.getWeeklyForecast(API_ZIP_CODE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> view.updateWeeklyForecast(result) },
                        { error -> view.showError(error?.message ?: "No error message") }
                )
    }
}