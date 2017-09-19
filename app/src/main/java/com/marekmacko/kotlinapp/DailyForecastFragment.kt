package com.marekmacko.kotlinapp

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marekmacko.kotlinapp.data.DailyForecast
import com.marekmacko.kotlinapp.data.Temperature
import kotlinx.android.synthetic.main.fragment_daily_forecast.*


class DailyForecastFragment : Fragment() {

    companion object {
        private const val DAILY_FORECAST_KEY = "daily_forecast_key"

        fun newInstance(dailyForecast: DailyForecast): DailyForecastFragment {
            val args = Bundle()
            args.putSerializable(DAILY_FORECAST_KEY, dailyForecast)
            val dialog = DailyForecastFragment()
            dialog.arguments = args
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDailyForecastFromArgs()
    }

    private fun getDailyForecastFromArgs(): DailyForecast =
            arguments.getSerializable(DAILY_FORECAST_KEY) as DailyForecast

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_daily_forecast, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dailyForecast = getDailyForecastFromArgs()
        setupTitle(dailyForecast.date)
        bindViewsWithForecast(dailyForecast)
    }

    private fun setupTitle(date: String) {
        activity.title = date
    }

    private fun bindViewsWithForecast(dailyForecast: DailyForecast) {
        with(dailyForecast) {
            pressureValueView.text = pressure.toString()
            humidityValueView.text = speed.toString()
            bindTemperature(temp)
        }
    }

    private fun bindTemperature(temp: Temperature) {
        with(temp) {
            tempDayValueView.text = day.toString()
            tempMorningValueView.text = morn.toString()
            tempEveningValueView.text = eve.toString()
            tempNightValueView.text = night.toString()
            tempMinValueView.text = min.toString()
            tempMaxValueView.text = max.toString()
        }
    }
}