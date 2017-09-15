package com.marekmacko.kotlinapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.marekmacko.kotlinapp.data.WeeklyForecast
import com.marekmacko.kotlinapp.mvp.WeatherMvp
import com.marekmacko.kotlinapp.mvp.WeatherPresenter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : AppCompatActivity(), WeatherMvp.View {

    private lateinit var presenter: WeatherMvp.Presenter
    @Inject lateinit var weatherRepository: WeatherRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
//        DaggerWeatherComponent.create().inject(this)
//DaggerWeatherComponent.create().inject(this)

        WeatherPresenter(this)
    }

    override fun setPresenter(presenter: WeatherMvp.Presenter) {
        this.presenter = presenter
        presenter.fetchForecast()
    }

    override fun updateWeeklyForecast(weeklyForecast: WeeklyForecast) {
        forecastListView.adapter = ForecastAdapter(weeklyForecast) {
            toast(it.date.toString())
        }
    }

    override fun showError(message: String) {
        toast(message)
    }
}
