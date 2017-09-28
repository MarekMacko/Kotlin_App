package com.marekmacko.kotlinapp.ui

import com.marekmacko.kotlinapp.App
import com.marekmacko.kotlinapp.AppComponent
import com.marekmacko.kotlinapp.AppModule
import com.marekmacko.kotlinapp.BuildConfig
import com.marekmacko.kotlinapp.mvp.WeatherPresenter
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import it.cosenonjaviste.daggermock.DaggerMock
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class WeeklyForecastFragmentTest {

    @get:Rule
    val rule = DaggerMock.rule<AppComponent>(AppModule(getApp())) {
        set { getApp().component = it }
    }

    private fun getApp() = RuntimeEnvironment.application as App

    private lateinit var weeklyForecastFragment: WeeklyForecastFragment
    private val presenter: WeatherPresenter = mock()

    @Test
    fun dataIsSetup() {
        doAnswer {
            assertNotNull(weeklyForecastFragment)
        }.whenever(presenter).fetchForecast()

        weeklyForecastFragment = Robolectric.buildFragment(WeeklyForecastFragment::class.java)
                .create().start().resume().visible().get()

        assertNotNull(weeklyForecastFragment)
    }
}
