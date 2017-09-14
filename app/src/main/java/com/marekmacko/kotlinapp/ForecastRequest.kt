package com.marekmacko.kotlinapp

import com.google.gson.Gson
import java.net.URL


class ForecastRequest(private val zipCode: String) {

    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/" +
                "forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
        private val JSON_CONVERTER = Gson()
    }

    fun execute(): WeeklyForecast = JSON_CONVERTER.fromUrl(COMPLETE_URL + zipCode)

    private fun Gson.fromUrl(url: String): WeeklyForecast {
        val jsonString = URL(url).readText()
        return fromJson(jsonString, WeeklyForecast::class.java)
    }
}
