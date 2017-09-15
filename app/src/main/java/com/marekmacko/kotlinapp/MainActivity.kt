package com.marekmacko.kotlinapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.marekmacko.kotlinapp.api.WeatherService
import com.marekmacko.kotlinapp.data.WeeklyForecast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    private val API_ZIP_CODE = "94043"
    private var disposable: Disposable? = null
    private val weatherService by lazy {
        WeatherService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        forecastListView.layoutManager = LinearLayoutManager(this)
        loadForecast()
    }

    private fun loadForecast() {
        disposable = weatherService.getWeeklyForecast(API_ZIP_CODE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> showWeekly(result) },
                        { error -> showError(error.message) }
                )
    }

    private fun showError(message: String?) {
        if (message != null) {
            toast(message)
        }
    }

    private fun showWeekly(weeklyForecast: WeeklyForecast) {
        initAdapterWithWeeklyForecast(weeklyForecast)
    }

    private fun initAdapterWithWeeklyForecast(weeklyForecast: WeeklyForecast) {
        forecastListView.adapter = ForecastAdapter(weeklyForecast) { toast(it.date.toString()) }
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
