package com.marekmacko.kotlinapp.ui

import com.marekmacko.kotlinapp.BuildConfig
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class MainActivityTest {

    @Test
    fun activityNotNull() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        assertNotNull(activity)
    }
}
