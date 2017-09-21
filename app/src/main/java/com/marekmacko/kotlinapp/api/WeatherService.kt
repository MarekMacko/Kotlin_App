package com.marekmacko.kotlinapp.api

import com.marekmacko.kotlinapp.data.WeeklyForecast
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {

    @GET(REQUEST_PARAMS)
    fun getWeeklyForecast(@Query("q") zipCode: String): Observable<WeeklyForecast>

    companion object {
        const val BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/"
        private const val REQUEST_PARAMS = "daily?mode=json&units=metric&cnt=7" +
                "&APPID=15646a06818f61f7b8d7823ca833e1ce"
    }
}
