package com.marekmacko.kotlinapp.data

import com.google.gson.annotations.SerializedName

data class WeeklyForecast(val city: City,
                          @SerializedName("list") private val days: List<DailyForecast>) {

    val size: Int
        get() = days.size

    operator fun get(dayOfIndex: Int) = days[dayOfIndex]
}