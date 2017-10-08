package com.marekmacko.kotlinapp.ui

import android.support.v4.app.FragmentActivity
import com.marekmacko.kotlinapp.BuildConfig
import com.marekmacko.kotlinapp.MockData
import kotlinx.android.synthetic.main.fragment_daily_forecast.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class DailyForecastFragmentTest {

    private lateinit var fragment: DailyForecastFragment
    private lateinit var activityController: ActivityController<FragmentActivity>

    @Before
    fun setUp() {
        val forecast = MockData.getForecastList()[0]
        fragment = DailyForecastFragment.newInstance(forecast)
        activityController = Robolectric.buildActivity(FragmentActivity::class.java)
        activityController.get()
                .fragmentManager
                .beginTransaction()
                .add(fragment, null)
                .commit()
        activityController.create().start().resume().visible()
    }

    @Test
    fun titleIsSetup() = assertEquals(MockData.DATE_STRING, fragment.activity.title)

    @Test
    fun descriptionIsSetupProperly() =
            assertEquals(MockData.DESCRIPTION, fragment.descriptionView.text)

    @Test
    fun pressureIsSetupProperly() =
            assertEquals(MockData.DESCRIPTION, fragment.descriptionView.text)

    @Test
    fun humidityIsSetupProperly() =
            assertEquals(MockData.HUMIDITY.toString(), fragment.humidityValueView.text)

    @Test
    fun tempMaxIsSetupProperly() =
            assertEquals(MockData.TEMP_MAX.toString(), fragment.tempMaxValueView.text)

    @Test
    fun tempMinIsSetupProperly() =
            assertEquals(MockData.TEMP_MIN.toString(), fragment.tempMinValueView.text)

    @Test
    fun tempDayIsSetupProperly() =
            assertEquals(MockData.TEMP_DAY.toString(), fragment.tempDayValueView.text)

    @Test
    fun tempNightIsSetupProperly() =
            assertEquals(MockData.TEMP_NIGHT.toString(), fragment.tempNightValueView.text)

    @Test
    fun tempMorningIsSetupProperly() =
            assertEquals(MockData.TEMP_MORNING.toString(), fragment.tempMorningValueView.text)

    @Test
    fun tempEveningIsSetupProperly() =
        assertEquals(MockData.TEMP_EVENING.toString(), fragment.tempEveningValueView.text)
}
