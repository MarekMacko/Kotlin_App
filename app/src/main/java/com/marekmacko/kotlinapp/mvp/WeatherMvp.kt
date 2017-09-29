package com.marekmacko.kotlinapp.mvp

import com.marekmacko.kotlinapp.data.ui.ForecastShort


interface WeatherMvp {

    interface View {
        fun showLoading()

        fun hideLoading()

        fun updateWeeklyForecast(weeklyForecast: List<ForecastShort>)

        fun showError()

        fun hideError()
    }

    interface Presenter {
        fun fetchForecast()

        fun cancelFetch()
    }
}
