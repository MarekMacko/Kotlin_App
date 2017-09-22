package com.marekmacko.kotlinapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marekmacko.kotlinapp.data.ui.ForecastShort
import com.marekmacko.kotlinapp.util.loadFromUrl
import kotlinx.android.synthetic.main.item_forecast.view.*

class ForecastListAdapter(private val itemClick: (ForecastShort) -> Unit)
    : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    private var weeklyForecast: List<ForecastShort>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bindForecast(weeklyForecast!![position])

    override fun getItemCount(): Int = weeklyForecast?.size ?: 0

    fun setWeeklyForecast(weeklyForecast: List<ForecastShort>) {
        this.weeklyForecast = weeklyForecast
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View, private val itemClick: (ForecastShort) -> Unit)
        : RecyclerView.ViewHolder(view) {

        fun bindForecast(forecast: ForecastShort) = with(forecast) {
            itemView.icon.loadFromUrl(iconUrl)
            itemView.description.text = description
            itemView.maxTemperature.text = "$maxTemperature"
            itemView.minTemperature.text = "$minTemperature"
            itemView.date.text = date
            itemView.setOnClickListener { itemClick(this) }
        }
    }
}
