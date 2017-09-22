package com.marekmacko.kotlinapp.repository

import com.marekmacko.kotlinapp.ModelMapper
import com.marekmacko.kotlinapp.api.WeatherService
import com.marekmacko.kotlinapp.data.ui.ForecastShort
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val weatherService: WeatherService) {

    companion object {
        private const val API_ZIP_CODE = "94043"
    }

    fun getWeeklyForecast(onLoad: (List<ForecastShort>) -> Unit, onError: (String) -> Unit): Disposable =
            weatherService.getWeeklyForecast(API_ZIP_CODE)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map {
                        ModelMapper.convertResponseToForecastShort(it)
                    }
                    .subscribe(
                        { onLoad(it) },
                        { onError(it.message ?: "No message provided") }
                    )
}
