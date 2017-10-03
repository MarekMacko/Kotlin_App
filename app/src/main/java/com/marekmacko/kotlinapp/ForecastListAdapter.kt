package com.marekmacko.kotlinapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marekmacko.kotlinapp.data.ui.Forecast
import com.marekmacko.kotlinapp.util.loadFromUrl
import kotlinx.android.synthetic.main.item_forecast.view.*

class ForecastListAdapter(private val itemClick: (Forecast) -> Unit)
    : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    private val weeklyForecast: MutableList<Forecast> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bindForecast(weeklyForecast[position])

    override fun getItemCount() = weeklyForecast.size

    fun setWeeklyForecast(weeklyForecast: List<Forecast>) {
        this.weeklyForecast.clear()
        this.weeklyForecast.addAll(weeklyForecast)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View, private val itemClick: (Forecast) -> Unit)
        : RecyclerView.ViewHolder(view) {

        fun bindForecast(forecast: Forecast) = with(forecast) {
            itemView.icon.loadFromUrl(iconUrl)
            itemView.description.text = description
            itemView.maxTemperature.text = "${temperature.max}"
            itemView.minTemperature.text = "${temperature.min}"
            itemView.date.text = date
            itemView.setOnClickListener { itemClick(this) }
        }
    }
}
