package com.marekmacko.kotlinapp.ui

import com.marekmacko.kotlinapp.BuildConfig
import com.marekmacko.kotlinapp.MockData
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.util.FragmentTestUtil

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class DailyForecastFragmentTest {

     @Test
    fun titleIsSetup() {
        val forecast = MockData.getForecast()
        val fragment = DailyForecastFragment.newInstance(forecast)
        FragmentTestUtil.startFragment(fragment)
        assertEquals(forecast.date, fragment.activity.title)
    }
}
