package com.marekmacko.kotlinapp.data.response

data class Weather(val description: String, private val icon: String) {

    val iconUrl: String
        get() =
            if (icon.isEmpty()) throw IllegalArgumentException()
            else "http://openweathermap.org/img/w/$icon.png"
}
