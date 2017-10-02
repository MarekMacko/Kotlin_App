package com.marekmacko.kotlinapp.ui

import android.view.View
import com.marekmacko.kotlinapp.BuildConfig
import com.marekmacko.kotlinapp.MockData
import com.marekmacko.kotlinapp.getDaggerMockRule
import com.marekmacko.kotlinapp.mvp.WeatherPresenter
import com.nhaarman.mockito_kotlin.*
import kotlinx.android.synthetic.main.fragment_weekly_forecast.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.FragmentController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class WeeklyForecastFragmentTest {

    @get:Rule
    val daggerMockRule = getDaggerMockRule()

    private val presenter: WeatherPresenter = mock()
    private lateinit var weeklyForecastFragment: WeeklyForecastFragment
    private lateinit var controller: FragmentController<WeeklyForecastFragment>

    @Before
    fun setUp() {
        controller = Robolectric.buildFragment(WeeklyForecastFragment::class.java)
        weeklyForecastFragment = controller.get()
    }

    @Test
    fun loadingViewIsVisible() {
        doAnswer {
            weeklyForecastFragment.showLoading()
        }.whenever(presenter).fetchForecast()

        controller.create().start()

        assertEquals(weeklyForecastFragment.loadingView.visibility, View.VISIBLE)
    }

    @Test
    fun loadingViewIsGone() {
        doAnswer {
            weeklyForecastFragment.hideLoading()
        }.whenever(presenter).fetchForecast()

        controller.create().start()

        assertEquals(weeklyForecastFragment.loadingView.visibility, View.INVISIBLE)
    }

    @Test
    fun errorAndRefreshIconAreVisible() {
        doAnswer {
            weeklyForecastFragment.showError()
        }.whenever(presenter).fetchForecast()

        controller.create().start()

        assertEquals(weeklyForecastFragment.errorView.visibility, View.VISIBLE)
        assertEquals(weeklyForecastFragment.refreshIconView.visibility, View.VISIBLE)
    }

    @Test
    fun errorAndRefreshIconAreInvisible() {
        doAnswer {
            weeklyForecastFragment.hideError()
        }.whenever(presenter).fetchForecast()

        controller.create().start()

        assertEquals(weeklyForecastFragment.errorView.visibility, View.INVISIBLE)
        assertEquals(weeklyForecastFragment.refreshIconView.visibility, View.INVISIBLE)
    }

    @Test
    fun fetchIsCanceledInOnPause() {
        controller.create().start().resume().visible().pause().stop().destroy()

        verify(presenter, times(1)).cancelFetch()
    }

    @Test
    fun dataIsSetup() {
        doAnswer {
            weeklyForecastFragment.updateWeeklyForecast(MockData.getWeeklyForecastList())
        }.whenever(presenter).fetchForecast()

        controller.create().start()

        assertEquals(weeklyForecastFragment.forecastListView.adapter.itemCount, 1)
    }
}
