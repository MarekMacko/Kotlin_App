package com.marekmacko.kotlinapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marekmacko.kotlinapp.data.DailyForecast
import com.marekmacko.kotlinapp.data.Temperature
import com.marekmacko.kotlinapp.data.Weather
import com.marekmacko.kotlinapp.data.WeeklyForecast
import com.marekmacko.kotlinapp.util.loadFromUrl
import kotlinx.android.synthetic.main.item_forecast.view.*

class ForecastListAdapter(private val itemClick: (DailyForecast) -> Unit)
    : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    private var weeklyForecast: WeeklyForecast? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bindDailyForecast(weeklyForecast!![position])

    override fun getItemCount(): Int = weeklyForecast?.size ?: 0

    fun setItems(weeklyForecast: WeeklyForecast) {
        this.weeklyForecast = weeklyForecast
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View, private val itemClick: (DailyForecast) -> Unit)
        : RecyclerView.ViewHolder(view) {

        fun bindDailyForecast(dailyForecast: DailyForecast) = with(dailyForecast) {
            bindWeather(weather)
            bindTemperature(temp)
            itemView.date.text = date
            itemView.setOnClickListener { itemClick(this) }
        }

        private fun bindWeather(weather: List<Weather>?) {
            if (weather == null || weather.isEmpty()) {
                return
            }
            with(weather[0]) {
                itemView.icon.loadFromUrl(iconUrl)
                itemView.description.text = description
            }
        }

        private fun bindTemperature(temperature: Temperature?) {
            temperature?.apply {
                itemView.maxTemperature.text = "$max"
                itemView.minTemperature.text = "$min"
            }
        }
    }
}

