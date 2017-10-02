package com.marekmacko.kotlinapp

import com.marekmacko.kotlinapp.data.response.*
import com.marekmacko.kotlinapp.data.response.Temperature as TemperatureResponse
import com.marekmacko.kotlinapp.data.ui.Forecast
import com.marekmacko.kotlinapp.data.ui.Temperature
import com.tngtech.java.junit.dataprovider.DataProvider


class MockData {


    companion object {
        const val DATE_STRING = "2017-10-02"
        const val DATE_LONG = 1506970800L
        const val DESCRIPTION = "little rain"
        const val TEMP_MAX = 20f
        const val TEMP_MIN = 10f
        const val TEMP_DAY = 15f
        const val TEMP_NIGHT = 13f
        const val TEMP_MORNING = 11f
        const val TEMP_EVENING = 12f
        const val HUMIDITY = 60
        const val PRESSURE = 80f
        const val ICON_URL = "url" // TODO
        const val ICON_CODE = "10d" // TODO
        const val COORD_LON = -122.0839f
        const val COORD_LAT = 37.3861f
        const val CITY_ID = 123L
        const val CITY_NAME = "Szczecin"
        const val CITY_COUNTRY = "PL"
        const val CITY_POPULATION = 41

        @DataProvider
        @JvmStatic
        fun getWeeklyForecastResponse(): Array<Array<Any>> {
            val coordinates = Coordinates(COORD_LON, COORD_LAT)
            val city = City(CITY_ID, CITY_NAME, coordinates, CITY_COUNTRY, CITY_POPULATION)
            val weather = listOf(Weather(DESCRIPTION, ICON_CODE))
            val dailyForecast = DailyForecast(DATE_LONG, getTemperatureResponse(), PRESSURE, HUMIDITY, weather)
            val weeklyForecast = WeeklyForecast(city, arrayListOf(dailyForecast))
            return arrayOf(arrayOf<Any>(weeklyForecast))
        }

        private fun getTemperatureResponse() =
                TemperatureResponse(TEMP_DAY, TEMP_MIN, TEMP_MAX, TEMP_NIGHT, TEMP_EVENING, TEMP_MORNING)

        private fun getTemperature() =
                Temperature(TEMP_MAX, TEMP_MIN, TEMP_DAY, TEMP_NIGHT, TEMP_MORNING, TEMP_EVENING)

        @DataProvider
        @JvmStatic
        fun getWeeklyForecastArray(): Array<Array<Any>> = arrayOf(arrayOf<Any>(getForecastList()))

        @JvmStatic
        fun getForecastList(): List<Forecast> {
            val forecast = Forecast(DATE_STRING, DESCRIPTION, getTemperature(), HUMIDITY, PRESSURE, ICON_URL)
            return (listOf(forecast))
        }
    }
}
