package com.marekmacko.kotlinapp.mvp

import dagger.Module
import dagger.Provides


@Module
class WeatherPresenterModule(private val view: WeatherMvp.View) {

    @Provides
    fun provideWeatherView(): WeatherMvp.View = view
}
