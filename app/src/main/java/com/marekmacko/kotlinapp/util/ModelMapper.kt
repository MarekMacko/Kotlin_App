package com.marekmacko.kotlinapp.util

import com.marekmacko.kotlinapp.data.response.DailyForecast
import com.marekmacko.kotlinapp.data.response.WeeklyForecast
import com.marekmacko.kotlinapp.data.ui.Forecast
import com.marekmacko.kotlinapp.data.ui.Temperature


class ModelMapper {

    companion object {

        fun convertResponseToForecast(weeklyForecast: WeeklyForecast): List<Forecast> =
                weeklyForecast.days.map {
                    convertDailyForecast(it)
                }

        private fun convertDailyForecast(dailyForecast: DailyForecast) = with(dailyForecast) {
            val weather = weather[0]
            with(temp) {
                val temperature = Temperature(max, min, day, night, morn, eve)
                Forecast(date, weather.description, temperature, humidity, pressure, weather.iconUrl)
            }
        }
    }
}
