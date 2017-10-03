package com.marekmacko.kotlinapp.data

import com.marekmacko.kotlinapp.MockData
import com.marekmacko.kotlinapp.data.response.Weather
import org.junit.Test

import org.junit.Assert.*

class WeatherTest {

    @Test
    fun getIconUrl() {
        val weather = Weather(MockData.DESCRIPTION, MockData.ICON)
        assertEquals(weather.iconUrl, MockData.ICON_URL)
    }
}
