package com.marekmacko.kotlinapp.util

import com.marekmacko.kotlinapp.MockData
import com.marekmacko.kotlinapp.data.response.WeeklyForecast
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(DataProviderRunner::class)
class ModelMapperTest {

    private val modelMapper = ModelMapper()

    @Test
    @UseDataProvider("getWeeklyForecastResponse", location = arrayOf(MockData::class))
    fun convertResponseToForecastCheckBasicInfo(weeklyForecast: WeeklyForecast) {
        val forecast = modelMapper.convertResponseToForecast(weeklyForecast)[0]

        with(weeklyForecast[0]) {
            assertEquals(date, forecast.date)
            assertEquals(weather[0].description, forecast.description)
            assertEquals(humidity, forecast.humidity)
            assertEquals(pressure, forecast.pressure)
        }
    }

    @Test
    @UseDataProvider("getWeeklyForecastResponse", location = arrayOf(MockData::class))
    fun convertResponseToForecastCheckTemperature(weeklyForecast: WeeklyForecast) {
        val forecast = modelMapper.convertResponseToForecast(weeklyForecast)[0]

        with(weeklyForecast[0].temp) {
            assertEquals(max, forecast.temperature.max)
            assertEquals(min, forecast.temperature.min)
            assertEquals(day, forecast.temperature.day)
            assertEquals(night, forecast.temperature.night)
            assertEquals(morn, forecast.temperature.morning)
            assertEquals(eve, forecast.temperature.evening)
        }
    }
}
