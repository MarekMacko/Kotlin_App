package com.marekmacko.kotlinapp.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.DateFormat
import java.util.*


private val dateFormatter by lazy {
    DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
}

data class WeeklyForecast(val city: City,
                          @SerializedName("list") private val days: List<DailyForecast>) {

    val size: Int
        get() = days.size

    operator fun get(dayOfIndex: Int) = days[dayOfIndex]
}

data class City(val id: Long, val name: String, val coord: Coordinates,
                val country: String, val population: Int)

data class DailyForecast(private val dt: Long, val temp: Temperature, val pressure: Float,
                         val humidity: Int, val weather: List<Weather>, val speed: Float,
                         val deg: Int, val clouds: Int, val rain: Float) : Serializable {
    val date: String
        get() = dateFormatter.format(dt * 1000)
}

data class Coordinates(val lon: Float, val lat: Float)

data class Temperature(val day: Float, val min: Float, val max: Float,
                       val night: Float,
                       @SerializedName("eve") val evening: Float,
                       @SerializedName("morn") val morning: Float)

data class Weather(val id: Long, val main: String, val description: String,
                   @SerializedName("icon") private val iconCode: String) {
    val iconUrl: String
        get() = "http://openweathermap.org/img/w/$iconCode.png"
}
