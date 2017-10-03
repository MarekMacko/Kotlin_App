package com.marekmacko.kotlinapp.repository

import com.marekmacko.kotlinapp.api.WeatherService
import com.marekmacko.kotlinapp.data.ui.Forecast
import com.marekmacko.kotlinapp.util.ModelMapper
import io.reactivex.Observable


class WeatherRepository(private val weatherService: WeatherService,
                        private val modelMapper: ModelMapper) {

    companion object {
        private const val API_ZIP_CODE = "94043"
    }

    fun getWeeklyForecast(): Observable<List<Forecast>> =
            weatherService.getWeeklyForecast(API_ZIP_CODE)
                    .map {
                        modelMapper.convertResponseToForecast(it)
                    }
}
