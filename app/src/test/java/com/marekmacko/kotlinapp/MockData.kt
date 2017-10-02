package com.marekmacko.kotlinapp

import com.marekmacko.kotlinapp.data.response.*
import com.marekmacko.kotlinapp.data.response.Temperature as TemperatureResponse
import com.marekmacko.kotlinapp.data.ui.Forecast
import com.marekmacko.kotlinapp.data.ui.Temperature
import com.tngtech.java.junit.dataprovider.DataProvider


class MockData {
    // TODO: fix data
    companion object {
        const val DATE = ""
        const val DESCRIPTION = "little rain"
        const val TEMP_MAX = 20f
        const val TEMP_MIN = 10f
        const val TEMP_DAY = 10f
        const val TEMP_NIGHT = 15f
        const val TEMP_MORNING = 12f
        const val TEMP_EVENING = 12f
        const val HUMIDITY = 80
        const val PRESSURE = 80f
        const val ICON_URL = "url"
        val TEMPERATURE = Temperature(12f, 12f, 12f, 12f, 12f, 12f)

        @DataProvider
        @JvmStatic
        fun getWeeklyForecastResponse(): Array<Array<Any>> {
            val coordinates = Coordinates(12f, 23f)
            val city = City(1, "", coordinates, "Poland", 345)
            val weather = listOf(Weather(1, "Rain", DESCRIPTION, "10d"))
            val dailyForecast = DailyForecast(1234, getTemperatureResponse(), 12f, 12, weather, 12f, 12, 12, 12f)
            val weeklyForecast = WeeklyForecast(city, arrayListOf(dailyForecast))
            return arrayOf(arrayOf<Any>(weeklyForecast))
        }

        private fun getTemperatureResponse() =
                TemperatureResponse(TEMP_DAY, TEMP_MIN, TEMP_MAX, TEMP_NIGHT, TEMP_EVENING, TEMP_MORNING)

        @DataProvider
        @JvmStatic
        fun getWeeklyForecastArray(): Array<Array<Any>> {
            val forecastShort = getForecast()
            return arrayOf(arrayOf<Any>(listOf(forecastShort)))
        }

        @JvmStatic
        fun getForecast() = Forecast(DATE, DESCRIPTION, TEMPERATURE, HUMIDITY, PRESSURE, ICON_URL)

        @JvmStatic
        fun getWeeklyForecastList(): List<Forecast> {
            val forecastShort = Forecast(MockData.DATE, MockData.DESCRIPTION, MockData.TEMPERATURE,
                    MockData.HUMIDITY, MockData.PRESSURE, MockData.ICON_URL)
            return (listOf(forecastShort))
        }
    }
}
