package com.marekmacko.kotlinapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private val API_ZIP_CODE = "94043"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        forecastListView.layoutManager = LinearLayoutManager(this)
        doAsync {
            val weeklyForecast = ForecastRequest(API_ZIP_CODE).execute()
            uiThread {
                initAdapterWithWeeklyForecast(weeklyForecast)
            }
        }
    }

    private fun initAdapterWithWeeklyForecast(weeklyForecast: WeeklyForecast) {
        forecastListView.adapter = ForecastAdapter(weeklyForecast) { toast(it.date.toString()) }
    }
}
