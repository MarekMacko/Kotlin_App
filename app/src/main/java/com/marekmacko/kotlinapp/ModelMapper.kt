package com.marekmacko.kotlinapp

import com.marekmacko.kotlinapp.data.response.DailyForecast
import com.marekmacko.kotlinapp.data.response.WeeklyForecast
import com.marekmacko.kotlinapp.data.ui.ForecastShort


class ModelMapper {

    companion object {

        fun convertResponseToForecastShort(weeklyForecast: WeeklyForecast): List<ForecastShort> {
            val forecastsShort = ArrayList<ForecastShort>() // TODO: find better solution
            weeklyForecast.days.forEach {
                forecastsShort.add(convertDailyForecast(it))
            }
            return forecastsShort
        }

        private fun convertDailyForecast(dailyForecast: DailyForecast) = with(dailyForecast) {
            val weather = weather[0]
            ForecastShort(date, weather.description, temp.min.toInt(), temp.max.toInt(), weather.iconUrl)
        }
    }
}
