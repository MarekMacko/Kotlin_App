package com.marekmacko.kotlinapp.util

import com.marekmacko.kotlinapp.data.response.DailyForecast
import com.marekmacko.kotlinapp.data.response.WeeklyForecast
import com.marekmacko.kotlinapp.data.ui.Forecast
import com.marekmacko.kotlinapp.data.ui.Temperature


class ModelMapper {

    companion object {

        fun convertResponseToForecast(weeklyForecast: WeeklyForecast): List<Forecast> {
            val forecastsShort = ArrayList<Forecast>() // TODO: find better solution
            weeklyForecast.days.forEach {
                forecastsShort.add(convertDailyForecast(it))
            }
            return forecastsShort
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
