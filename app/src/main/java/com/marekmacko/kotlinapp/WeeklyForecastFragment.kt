package com.marekmacko.kotlinapp

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marekmacko.kotlinapp.data.DailyForecast
import com.marekmacko.kotlinapp.data.WeeklyForecast
import com.marekmacko.kotlinapp.mvp.WeatherMvp
import com.marekmacko.kotlinapp.mvp.WeatherPresenter
import com.marekmacko.kotlinapp.repository.WeatherRepository
import kotlinx.android.synthetic.main.fragment_weekly_forecast.*
import org.jetbrains.anko.toast
import javax.inject.Inject


class WeeklyForecastFragment : Fragment(), WeatherMvp.View {

    private lateinit var presenter: WeatherMvp.Presenter
    @Inject lateinit var weatherRepository: WeatherRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        init()
        return inflater.inflate(R.layout.fragment_weekly_forecast, container, false)
    }

    private fun init() {
        activity.title = getString(R.string.forecasts)
        DaggerWeatherComponent.create().inject(this)
        WeatherPresenter(this, weatherRepository)
    }

    override fun setPresenter(presenter: WeatherMvp.Presenter) {
        this.presenter = presenter
        presenter.fetchForecast()
    }

    override fun updateWeeklyForecast(weeklyForecast: WeeklyForecast) {
        forecastListView.adapter = ForecastAdapter(weeklyForecast) {
            showDayForecastDialog(it)
        }
    }

    private fun showDayForecastDialog(dailyForecast: DailyForecast) {
        val fragment = DailyForecastFragment.newInstance(dailyForecast)
        fragmentManager.beginTransaction()
                .replace(R.id.mainContainer, fragment)
                .addToBackStack(null)
                .commit()
    }

    override fun showError(message: String) = toast(message)
}