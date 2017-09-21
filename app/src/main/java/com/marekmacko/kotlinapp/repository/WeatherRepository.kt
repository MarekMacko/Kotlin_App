package com.marekmacko.kotlinapp.repository

import com.marekmacko.kotlinapp.data.WeeklyForecast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val remoteWeatherRepository: RemoteWeatherRepository) {

    fun getWeeklyForecast(onLoad: (WeeklyForecast) -> Unit, onError: (String) -> Unit): Disposable =
            remoteWeatherRepository.getWeeklyForecast()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { onLoad(it) },
                            { onError(it.message ?: "No message provided") }
                    )
}
