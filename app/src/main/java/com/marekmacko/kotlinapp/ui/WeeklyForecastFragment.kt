package com.marekmacko.kotlinapp.ui

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marekmacko.kotlinapp.App
import com.marekmacko.kotlinapp.ForecastListAdapter
import com.marekmacko.kotlinapp.R
import com.marekmacko.kotlinapp.WeeklyForecastModule
import com.marekmacko.kotlinapp.data.ui.ForecastShort
import com.marekmacko.kotlinapp.mvp.WeatherMvp
import com.marekmacko.kotlinapp.mvp.WeatherPresenter
import kotlinx.android.synthetic.main.fragment_weekly_forecast.*
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
        loadingView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingView.visibility = View.INVISIBLE
    }

    override fun updateWeeklyForecast(weeklyForecast: List<ForecastShort>) {
        forecastListAdapter.setWeeklyForecast(weeklyForecast)
    }

    override fun showError() {
        errorView.visibility = View.VISIBLE
        refreshIconView.visibility = View.VISIBLE
    }

    override fun hideError() {
        errorView.visibility = View.INVISIBLE
        refreshIconView.visibility = View.INVISIBLE
    }

    private fun init() {
        activity.title = getString(R.string.forecasts)
        initAdapterWithList()
        initClickListeners()
        initInjections()
    }

    private fun initClickListeners() {
        refreshIconView.setOnClickListener { presenter.fetchForecast() }
    }

    private fun initAdapterWithList() {
        forecastListAdapter = ForecastListAdapter {
            startDailyForecastFragment(it)
        }
        forecastListView.adapter = forecastListAdapter
    }

    private fun initInjections() {
        val app = activity.application as App
        app.component.weeklyForecastComponentBuilder()
                .weeklyForecastModule(WeeklyForecastModule(this))
                .build()
                .inject(this)
    }

    private fun startDailyForecastFragment(forecast: ForecastShort) {
        val fragment = DailyForecastFragment.newInstance(forecast)
        fragmentManager.beginTransaction()
                .replace(R.id.mainContainer, fragment)
                .addToBackStack(null)
                .commit()
    }
}
