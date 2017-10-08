package com.marekmacko.kotlinapp

import com.marekmacko.kotlinapp.api.NetworkModule
import com.marekmacko.kotlinapp.ui.WeeklyForecastFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(WeeklyForecastModule::class, NetworkModule::class))
interface WeeklyForecastComponent {

    fun inject(weeklyForecastFragment: WeeklyForecastFragment)

    @Subcomponent.Builder
    interface Builder {
        fun weeklyForecastModule(module: WeeklyForecastModule): Builder

        fun build(): WeeklyForecastComponent
    }
}
