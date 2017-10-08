package com.marekmacko.kotlinapp.data.response

data class City(val id: Long, val name: String,
                val coord: Coordinates, val country: String,
                val population: Int)
