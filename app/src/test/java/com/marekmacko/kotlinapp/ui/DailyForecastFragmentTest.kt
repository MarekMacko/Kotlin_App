package com.marekmacko.kotlinapp.ui

import com.marekmacko.kotlinapp.BuildConfig
import com.marekmacko.kotlinapp.MockData
import kotlinx.android.synthetic.main.fragment_daily_forecast.*
import kotlinx.android.synthetic.main.item_forecast.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.util.FragmentTestUtil

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class DailyForecastFragmentTest {

    private lateinit var fragment: DailyForecastFragment

    @Before
    fun setUp() {
        fragment = DailyForecastFragment.newInstance(MockData.getForecast())
        FragmentTestUtil.startFragment(fragment)
    }

    @Test
    fun titleIsSetup() {
        assertEquals(MockData.DATE, fragment.activity.title)
    }

    @Test
    fun forecastDataIsSetup() {
        // TODO: fix order either in xml file
        assertEquals(MockData.PRESSURE, fragment.description.text)
        assertEquals(MockData.HUMIDITY, fragment.humidityValueView.text)
        assertEquals(MockData.TEMP_DAY, fragment.tempDayValueView.text)
        assertEquals(MockData.TEMP_MORNING, fragment.tempMorningValueView.text)
        assertEquals(MockData.TEMP_EVENING, fragment.tempEveningValueView.text)
        assertEquals(MockData.TEMP_NIGHT, fragment.tempNightValueView.text)
        assertEquals(MockData.TEMP_MAX, fragment.tempMaxValueView.text)
        assertEquals(MockData.TEMP_MIN, fragment.tempMinValueView.text)
    }
}
