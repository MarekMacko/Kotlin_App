package com.marekmacko.kotlinapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.marekmacko.kotlinapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startWeeklyForecastFragment()
    }

    private fun startWeeklyForecastFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.mainContainer, WeeklyForecastFragment())
                .commit()
    }
}
