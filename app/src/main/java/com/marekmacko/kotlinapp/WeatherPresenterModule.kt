package com.marekmacko.kotlinapp

import com.marekmacko.kotlinapp.mvp.WeatherMvp
import dagger.Module
import dagger.Provides


@Module
class WeatherPresenterModule(private val view: WeatherMvp.View) {

    @Provides
    fun provideWeatherView(): WeatherMvp.View = view
}