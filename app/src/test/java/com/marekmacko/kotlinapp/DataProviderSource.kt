package com.marekmacko.kotlinapp

import com.marekmacko.kotlinapp.data.response.*
import com.tngtech.java.junit.dataprovider.DataProvider


class DataProviderSource {

    companion object {
        const val EXPECTED_DESCRIPTION = "little rain"
        const val EXPECTED_MIN_TEMPERATURE = 10
        const val EXPECTED_MAX_TEMPERATURE = 20

        @DataProvider
        @JvmStatic
        fun getWeeklyForecast(): Array<Array<Any>> {
            val coordinates = Coordinates(12f, 23f)
            val city = City(1, "", coordinates, "Poland", 345)
            val weather = listOf(Weather(1, "Rain", EXPECTED_DESCRIPTION, "10d"))
            val dailyForecast = DailyForecast(1234, getTemperature(), 12f, 12, weather, 12f, 12, 12, 12f)
            val weeklyForecast = WeeklyForecast(city, arrayListOf(dailyForecast))
            return arrayOf(arrayOf<Any>(weeklyForecast))
        }

        private fun getTemperature() =
                Temperature(12f, EXPECTED_MIN_TEMPERATURE.toFloat(),
                        EXPECTED_MAX_TEMPERATURE.toFloat(), 12f, 12f, 12f)
    }
}