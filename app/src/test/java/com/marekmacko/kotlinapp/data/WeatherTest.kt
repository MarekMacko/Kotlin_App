package com.marekmacko.kotlinapp.data

import org.junit.Test

import org.junit.Assert.*

class WeatherTest {

    @Test
    fun getIconUrl() {
        val iconCode = "10d"
        val expectedIconUrl = "http://openweathermap.org/img/w/$iconCode.png"
        val weather = Weather(1, "", "", iconCode)
        assertEquals(weather.iconUrl, expectedIconUrl)
    }
}