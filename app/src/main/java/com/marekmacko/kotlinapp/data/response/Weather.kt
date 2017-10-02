package com.marekmacko.kotlinapp.data.response

import com.google.gson.annotations.SerializedName

data class Weather(val description: String,
                   @SerializedName("icon") private val iconCode: String) {

    val iconUrl: String
        get() = "http://openweathermap.org/img/w/$iconCode.png"
}
