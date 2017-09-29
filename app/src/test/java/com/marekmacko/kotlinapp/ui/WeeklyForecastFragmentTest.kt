package com.marekmacko.kotlinapp.ui

import com.marekmacko.kotlinapp.BuildConfig
import com.marekmacko.kotlinapp.MockData
import com.marekmacko.kotlinapp.data.ui.ForecastShort
import com.marekmacko.kotlinapp.getDaggerMockRule
import com.marekmacko.kotlinapp.mvp.WeatherPresenter
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.android.synthetic.main.fragment_weekly_forecast.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class WeeklyForecastFragmentTest {

    @get:Rule val daggerMockRule = getDaggerMockRule()

    private lateinit var weeklyForecastFragment: WeeklyForecastFragment
    private val presenter: WeatherPresenter = mock()

    @Test
    fun dataIsSetup() {
        doAnswer {
            weeklyForecastFragment.updateWeeklyForecast(getWeeklyForecastShort())
        }.whenever(presenter).fetchForecast()

        val controller = Robolectric.buildFragment(WeeklyForecastFragment::class.java)
        weeklyForecastFragment = controller.get()
        controller.create().start()

        assertEquals(weeklyForecastFragment.forecastListView.adapter.itemCount, 1)
    }

    private fun getWeeklyForecastShort(): List<ForecastShort> {
        val forecastShort = ForecastShort(MockData.DATE, MockData.DESCRIPTION,
                MockData.MIN_TEMPERATURE, MockData.MAX_TEMPERATURE, MockData.ICON_URL)
        return (listOf(forecastShort))
    }
}
