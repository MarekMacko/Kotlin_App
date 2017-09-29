package com.marekmacko.kotlinapp.mvp

import com.marekmacko.kotlinapp.data.ui.Forecast


interface WeatherMvp {

    interface View {
        fun showLoading()

        fun hideLoading()

        fun updateWeeklyForecast(weeklyForecast: List<Forecast>)

        fun showError()

        fun hideError()
    }

    interface Presenter {
        fun fetchForecast()

        fun cancelFetch()
    }
}
