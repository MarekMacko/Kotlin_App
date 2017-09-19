package com.marekmacko.kotlinapp.mvp

import com.marekmacko.kotlinapp.base.BasePresenter
import com.marekmacko.kotlinapp.base.BaseView
import com.marekmacko.kotlinapp.data.WeeklyForecast


interface WeatherMvp {

    interface View : BaseView {
        fun showLoading()

        fun hideLoading()

        fun updateWeeklyForecast(weeklyForecast: WeeklyForecast)
    }

    interface Presenter : BasePresenter {
        fun fetchForecast()

        fun cancelFetch()
    }
}
