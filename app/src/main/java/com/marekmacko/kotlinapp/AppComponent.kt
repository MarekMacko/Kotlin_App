package com.marekmacko.kotlinapp

import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun weeklyForecastComponentBuilder(): WeeklyForecastComponent.Builder
}
