package com.marekmacko.kotlinapp.repository

import com.marekmacko.kotlinapp.ModelMapper
import com.marekmacko.kotlinapp.api.WeatherService
import com.marekmacko.kotlinapp.data.ui.ForecastShort
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RemoteWeatherRepository(private val weatherService: WeatherService) {

    companion object {
        private const val API_ZIP_CODE = "94043"
    }

    fun getWeeklyForecast(): Observable<List<ForecastShort>> =
            weatherService.getWeeklyForecast(API_ZIP_CODE)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map {
                        ModelMapper.convertResponseToForecastShort(it)
                    }
}
