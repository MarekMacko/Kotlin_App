package com.marekmacko.kotlinapp.ui

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marekmacko.kotlinapp.DaggerWeatherComponent
import com.marekmacko.kotlinapp.ForecastListAdapter
import com.marekmacko.kotlinapp.R
import com.marekmacko.kotlinapp.data.DailyForecast
import com.marekmacko.kotlinapp.data.WeeklyForecast
import com.marekmacko.kotlinapp.mvp.WeatherMvp
import com.marekmacko.kotlinapp.mvp.WeatherPresenter
import com.marekmacko.kotlinapp.mvp.WeatherPresenterModule
import com.marekmacko.kotlinapp.repository.WeatherRepository
import kotlinx.android.synthetic.main.fragment_weekly_forecast.*
import org.jetbrains.anko.toast
import javax.inject.Inject


class WeeklyForecastFragment : Fragment(), WeatherMvp.View {

    @Inject lateinit var presenter: WeatherPresenter
    @Inject lateinit var weatherRepository: WeatherRepository
    private lateinit var forecastListAdapter: ForecastListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_weekly_forecast, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        activity.title = getString(R.string.forecasts)
        initAdapterWithList()
        DaggerWeatherComponent.builder()
                .weatherPresenterModule(WeatherPresenterModule(this))
                .build()
                .inject(this)
    }

    private fun initAdapterWithList() {
        forecastListAdapter = ForecastListAdapter(null) {
            startDailyForecastFragment(it)
        }
        forecastListView.adapter = forecastListAdapter
    }

    override fun onStart() {
        super.onStart()
        presenter.fetchForecast()
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun updateWeeklyForecast(weeklyForecast: WeeklyForecast) {
        forecastListAdapter.setItemsAndNotify(weeklyForecast)
    }

    private fun startDailyForecastFragment(dailyForecast: DailyForecast) {
        val fragment = DailyForecastFragment.newInstance(dailyForecast)
        fragmentManager.beginTransaction()
                .replace(R.id.mainContainer, fragment)
                .addToBackStack(null)
                .commit()
    }

    override fun showError(message: String) = toast(message)

    override fun onPause() {
        super.onPause()
        presenter.cancelFetch()
    }
}