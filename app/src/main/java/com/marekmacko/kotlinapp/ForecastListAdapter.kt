package com.marekmacko.kotlinapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marekmacko.kotlinapp.data.DailyForecast
import com.marekmacko.kotlinapp.data.WeeklyForecast
import com.marekmacko.kotlinapp.util.loadFromUrl
import kotlinx.android.synthetic.main.item_forecast.view.*

class ForecastListAdapter(private var weeklyForecast: WeeklyForecast?,
                          private val itemClick: (DailyForecast) -> Unit)
    : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bindDailyForecast(weeklyForecast!![position])

    override fun getItemCount(): Int = weeklyForecast?.size?: 0

    fun setItemsAndNotify(weeklyForecast: WeeklyForecast) {
        this.weeklyForecast = weeklyForecast
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View, private val itemClick: (DailyForecast) -> Unit)
        : RecyclerView.ViewHolder(view) {

        fun bindDailyForecast(dailyForecast: DailyForecast) = with(dailyForecast) {
            val weather = weather[0] // TODO: API always return one element
            itemView.icon.loadFromUrl(weather.iconUrl)
            itemView.date.text = date
            itemView.description.text = weather.description
            itemView.maxTemperature.text = "${temp.max}"
            itemView.minTemperature.text = "${temp.min}"
            itemView.setOnClickListener { itemClick(this) }
        }
    }
}

