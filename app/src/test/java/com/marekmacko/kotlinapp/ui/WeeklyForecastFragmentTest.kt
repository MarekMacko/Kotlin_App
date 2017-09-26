package com.marekmacko.kotlinapp.ui

import android.app.Fragment
import com.marekmacko.kotlinapp.BuildConfig
import com.marekmacko.kotlinapp.R
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.FragmentController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class WeeklyForecastFragmentTest {

    private lateinit var fragment: Fragment
    private lateinit var controller: FragmentController<WeeklyForecastFragment>

    @Before
    fun setUp() {
        controller = Robolectric.buildFragment(WeeklyForecastFragment::class.java)
                .create()
                .start()
                .resume()
        fragment = controller.get()
    }

    @Test
    fun fragmentNotNull() {
        assertNotNull(fragment)
    }

    @Test
    fun titleIsSetup() {
        val activity = fragment.activity
        assertEquals(activity.title, activity.getString(R.string.forecasts))
    }
}
