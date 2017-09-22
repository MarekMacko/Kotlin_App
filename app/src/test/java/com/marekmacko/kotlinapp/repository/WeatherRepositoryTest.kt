package com.marekmacko.kotlinapp.repository

import com.marekmacko.kotlinapp.DataProviderSource
import com.marekmacko.kotlinapp.RxImmediateSchedulerRule
import com.marekmacko.kotlinapp.api.WeatherService
import com.marekmacko.kotlinapp.data.response.*
import com.marekmacko.kotlinapp.data.ui.ForecastShort
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@RunWith(DataProviderRunner::class)
class WeatherRepositoryTest {

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    val testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var weatherService: WeatherService

    @InjectMocks
    lateinit var remoteWeatherRepository: RemoteWeatherRepository

    lateinit var weatherRepository: WeatherRepository

    @Test
    @UseDataProvider("getWeeklyForecastShort", location = arrayOf(DataProviderSource::class))
    fun getWeeklyForecast(forecasts: List<ForecastShort>) {
        weatherRepository = WeatherRepository(remoteWeatherRepository)
        val onLoadMock = mock<(List<ForecastShort>) -> Unit>()
        val onErrorMock = mock<(String) -> Unit>()

        whenever(weatherService.getWeeklyForecast(any())).thenReturn(Observable.just(getWeeklyForecast()))
        whenever(remoteWeatherRepository.getWeeklyForecast()).thenReturn(Observable.just(forecasts))

        weatherRepository.getWeeklyForecast(onLoadMock, onErrorMock)
        verify(onLoadMock).invoke(forecasts)
    }

    // TODO: remove
    private fun getWeeklyForecast(): WeeklyForecast {
        val coordinates = Coordinates(12f, 23f)
        val city = City(1, "", coordinates, "Poland", 345)
        val weather = listOf(Weather(1, "Rain", DataProviderSource.DESCRIPTION, "10d"))
        val dailyForecast = DailyForecast(1234, DataProviderSource.getTemperature(), 12f, 12, weather, 12f, 12, 12, 12f)
        return WeeklyForecast(city, arrayListOf(dailyForecast))
    }
}
