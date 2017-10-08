package com.marekmacko.kotlinapp.data.ui

import java.io.Serializable


data class Forecast(val date: String, val description: String,
                    val temperature: Temperature, val humidity: Int,
                    val pressure:Float, val iconUrl: String): Serializable
