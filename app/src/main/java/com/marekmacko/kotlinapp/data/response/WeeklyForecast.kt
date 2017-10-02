package com.marekmacko.kotlinapp.data.response

import com.google.gson.annotations.SerializedName

data class WeeklyForecast(val city: City,
                          @SerializedName("list") val days: List<DailyForecast>) {

    operator fun get(dayOfIndex: Int) = days[dayOfIndex]
}
