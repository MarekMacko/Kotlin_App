package com.marekmacko.kotlinapp.mvp

import com.marekmacko.kotlinapp.data.WeeklyForecast


interface WeatherMvp {

    interface View {
        fun showLoading()

        fun hideLoading()

        fun updateWeeklyForecast(weeklyForecast: WeeklyForecast)

        fun showError(message: String)
    }

    interface Presenter {
        fun fetchForecast()

        fun cancelFetch()
    }
}
