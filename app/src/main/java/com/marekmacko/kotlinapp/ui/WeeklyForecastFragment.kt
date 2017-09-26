package com.marekmacko.kotlinapp.ui

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marekmacko.kotlinapp.DaggerWeatherComponent
import com.marekmacko.kotlinapp.ForecastListAdapter
import com.marekmacko.kotlinapp.R
import com.marekmacko.kotlinapp.api.NetworkModule
import com.marekmacko.kotlinapp.data.ui.ForecastShort
import com.marekmacko.kotlinapp.mvp.WeatherMvp
import com.marekmacko.kotlinapp.mvp.WeatherPresenter
import com.marekmacko.kotlinapp.mvp.WeatherPresenterModule
import kotlinx.android.synthetic.main.fragment_weekly_forecast.*
import org.jetbrains.anko.toast
import javax.inject.Inject


class WeeklyForecastFragment : Fragment(), WeatherMvp.View {

    @Inject lateinit var presenter: WeatherPresenter
    private lateinit var forecastListAdapter: ForecastListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_weekly_forecast, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onStart() {
        super.onStart()
        presenter.fetchForecast()
    }

    override fun onPause() {
        super.onPause()
        presenter.cancelFetch()
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun updateWeeklyForecast(weeklyForecast: List<ForecastShort>) {
        forecastListAdapter.setWeeklyForecast(weeklyForecast)
    }

    override fun showError(message: String) = toast(message)

    private fun init() {
        activity.title = getString(R.string.forecasts)
        initAdapterWithList()
        DaggerWeatherComponent.builder()
                .networkModule(NetworkModule(activity))
                .weatherPresenterModule(WeatherPresenterModule(this))
                .build()
                .inject(this)
    }

    private fun initAdapterWithList() {
        forecastListAdapter = ForecastListAdapter {
            startDailyForecastFragment(it)
        }
        forecastListView.adapter = forecastListAdapter
    }

    private fun startDailyForecastFragment(forecast: ForecastShort) {
        val fragment = DailyForecastFragment.newInstance(forecast)
        fragmentManager.beginTransaction()
                .replace(R.id.mainContainer, fragment)
                .addToBackStack(null)
                .commit()
    }
}
