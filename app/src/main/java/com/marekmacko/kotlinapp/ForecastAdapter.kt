package com.marekmacko.kotlinapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*
import java.text.DateFormat
import java.util.*

class ForecastAdapter(private val weeklyForecast: WeeklyForecast,
                      private val itemClick: (DailyForecast) -> Unit)
    : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bindDailyForecast(weeklyForecast[position])

    override fun getItemCount(): Int = weeklyForecast.size

    class ViewHolder(view: View, private val itemClick: (DailyForecast) -> Unit)
        : RecyclerView.ViewHolder(view) {

        fun bindDailyForecast(dailyForecast: DailyForecast) = with(dailyForecast) {
            val weather = weather[0] // TODO
            val iconUrl = generateIconUrl(weather.icon)
            Picasso.with(itemView.context).load(iconUrl).into(itemView.icon)
            itemView.date.text = convertDate(date)
            itemView.description.text = weather.description
            itemView.maxTemperature.text = "${temp.max}"
            itemView.minTemperature.text = "${temp.min}"
            itemView.setOnClickListener { itemClick(dailyForecast) }
        }

        private fun generateIconUrl(iconCode: String) =
                "http://openweathermap.org/img/w/$iconCode.png"

        private fun convertDate(date: Long): String {
            val dateFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
            return dateFormatter.format(date)
        }
    }
}

