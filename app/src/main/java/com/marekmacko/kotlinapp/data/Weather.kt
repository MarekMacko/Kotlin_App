package com.marekmacko.kotlinapp.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Weather(val id: Long, val main: String,
                   val description: String, @SerializedName("icon") private val iconCode: String)
    : Serializable {

    val iconUrl: String
        get() = "http://openweathermap.org/img/w/$iconCode.png"
}