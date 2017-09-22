package com.marekmacko.kotlinapp.repository

import com.marekmacko.kotlinapp.DataProviderSource
import com.marekmacko.kotlinapp.RxImmediateSchedulerRule
import com.marekmacko.kotlinapp.api.WeatherService
import com.marekmacko.kotlinapp.data.response.WeeklyForecast
import com.marekmacko.kotlinapp.data.ui.ForecastShort
import com.nhaarman.mockito_kotlin.*
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
class RemoteWeatherRepositoryTest {

    @Rule
    @JvmField
    val testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var weatherService: WeatherService

    @InjectMocks
    lateinit var weatherRepository: WeatherRepository

    private val onLoadMock = mock<(List<ForecastShort>) -> Unit>()
    private val onErrorMock = mock<(String) -> Unit>()

    @Test
    @UseDataProvider("getWeeklyForecast", location = arrayOf(DataProviderSource::class))
    fun getWeeklyForecastReturnData(weeklyForecast: WeeklyForecast) {
        whenever(weatherService.getWeeklyForecast(any())).thenReturn(Observable.just(weeklyForecast))
        whenever(onLoadMock.invoke(any())).thenReturn(any())

        weatherRepository.getWeeklyForecast(onLoadMock, onErrorMock)

        verify(onLoadMock, times(1)).invoke(any())
    }

    @Test
    @UseDataProvider("getWeeklyForecastShort", location = arrayOf(DataProviderSource::class))
    fun getWeeklyForecastReturnError(forecasts: List<ForecastShort>) {
        val errorMessage = "error"
        val error = Throwable(errorMessage)
        whenever(weatherService.getWeeklyForecast(any())).thenReturn(Observable.error(error))
        whenever(onErrorMock.invoke(any())).thenReturn(any())

        weatherRepository.getWeeklyForecast(onLoadMock, onErrorMock)

        verify(onErrorMock, times(1)).invoke(any())
    }
}