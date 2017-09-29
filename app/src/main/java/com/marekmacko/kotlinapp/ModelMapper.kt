package com.marekmacko.kotlinapp

import com.marekmacko.kotlinapp.data.response.DailyForecast
import com.marekmacko.kotlinapp.data.response.WeeklyForecast
import com.marekmacko.kotlinapp.data.ui.Forecast


class ModelMapper {

    companion object {

        fun convertResponseToForecastShort(weeklyForecast: WeeklyForecast): List<Forecast> {
            val forecastsShort = ArrayList<Forecast>() // TODO: find better solution
            weeklyForecast.days.forEach {
                forecastsShort.add(convertDailyForecast(it))
            }
            return forecastsShort
        }

        private fun convertDailyForecast(dailyForecast: DailyForecast) = with(dailyForecast) {
            val weather = weather[0]
            Forecast(date, weather.description, temp.min.toInt(), temp.max.toInt(), weather.iconUrl)
        }
    }
}
