package com.marekmacko.kotlinapp.data.ui

import java.io.Serializable


data class Forecast(val date: String, val description: String,
                    val minTemperature: Int, val maxTemperature: Int,
                    val iconUrl: String): Serializable
