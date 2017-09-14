package com.marekmacko.kotlinapp

import com.google.gson.annotations.SerializedName


data class WeeklyForecast(val city: City,
                          @SerializedName("list") private val days: List<DailyForecast>) {

    val size: Int
        get() = days.size

    operator fun get(dayOfIndex: Int) = days[dayOfIndex]
}

data class City(val id: Long, val name: String, val coord: Coordinates,
                val country: String, val population: Int)

data class DailyForecast(@SerializedName("dt") val date: Long,
                         val temp: Temperature, val pressure: Float,
                         val humidity: Int, val weather: List<Weather>,
                         val speed: Float, val deg: Int, val clouds: Int,
                         val rain: Float)

data class Coordinates(val lon: Float, val lat: Float)

data class Temperature(val day: Float, val min: Float, val max: Float,
                       val night: Float, val eve: Float, val morn: Float)

data class Weather(val id: Long, val min: String, val description: String,
                   val icon: String)
