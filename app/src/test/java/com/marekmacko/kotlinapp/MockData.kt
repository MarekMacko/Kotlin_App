package com.marekmacko.kotlinapp

import com.marekmacko.kotlinapp.data.response.*
import com.marekmacko.kotlinapp.data.ui.ForecastShort
import com.tngtech.java.junit.dataprovider.DataProvider


class MockData {

    companion object {
        const val DATE = ""
        const val DESCRIPTION = "little rain"
        const val MIN_TEMPERATURE = 10
        const val MAX_TEMPERATURE = 20
        const val ICON_URL = "url"

        @DataProvider
        @JvmStatic
        fun getWeeklyForecast(): Array<Array<Any>> {
            val coordinates = Coordinates(12f, 23f)
            val city = City(1, "", coordinates, "Poland", 345)
            val weather = listOf(Weather(1, "Rain", DESCRIPTION, "10d"))
            val dailyForecast = DailyForecast(1234, getTemperature(), 12f, 12, weather, 12f, 12, 12, 12f)
            val weeklyForecast = WeeklyForecast(city, arrayListOf(dailyForecast))
            return arrayOf(arrayOf<Any>(weeklyForecast))
        }

        private fun getTemperature() =
                Temperature(12f, MIN_TEMPERATURE.toFloat(),
                        MAX_TEMPERATURE.toFloat(), 12f, 12f, 12f)

        @DataProvider
        @JvmStatic
        fun getWeeklyForecastShort(): Array<Array<Any>> {
            val forecastShort = getForecastShort()
            return arrayOf(arrayOf<Any>(listOf(forecastShort)))
        }

        @JvmStatic
        fun getForecastShort() =
                ForecastShort(DATE, DESCRIPTION, MIN_TEMPERATURE, MAX_TEMPERATURE, ICON_URL)
    }
}
