package com.marekmacko.kotlinapp

import com.marekmacko.kotlinapp.api.WeatherService
import com.marekmacko.kotlinapp.mvp.WeatherMvp
import com.marekmacko.kotlinapp.mvp.WeatherPresenter
import com.marekmacko.kotlinapp.repository.WeatherRepository
import com.marekmacko.kotlinapp.ui.WeeklyForecastFragment
import dagger.Module
import dagger.Provides


@Module
class WeeklyForecastModule(private val weeklyForecastFragment: WeeklyForecastFragment) {

    @Provides
    fun providePresenter(view: WeatherMvp.View, repository: WeatherRepository) =
            WeatherPresenter(view, repository)

    @Provides
    fun provideView(): WeatherMvp.View = weeklyForecastFragment

    @Provides
    fun provideWeatherRepository(weatherService: WeatherService) = WeatherRepository(weatherService)
}
