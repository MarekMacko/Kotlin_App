package com.marekmacko.kotlinapp.repository

import com.marekmacko.kotlinapp.MockData
import com.marekmacko.kotlinapp.api.WeatherService
import com.marekmacko.kotlinapp.data.response.WeeklyForecast
import com.marekmacko.kotlinapp.util.ModelMapper
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.whenever
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@RunWith(DataProviderRunner::class)
class WeatherRepositoryTest {

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    private val weatherService: WeatherService = mock()
    private val modelMapper: ModelMapper = spy()

    @InjectMocks
    lateinit var weatherRepository: WeatherRepository

    @Test
    @UseDataProvider("getWeeklyForecastResponse", location = arrayOf(MockData::class))
    fun completeIsCalledOnDataReturn(weeklyForecast: WeeklyForecast) {
        whenever(weatherService.getWeeklyForecast(any())).thenReturn(Observable.just(weeklyForecast))

        val observable = weatherRepository.getWeeklyForecast().test()

        observable.assertNoErrors()
        observable.assertComplete()
        Assert.assertEquals(observable.valueCount(), weeklyForecast.days.size)
    }

    @Test
    fun errorIsCalled() {
        val errorMessage = "Not found"
        val error = Throwable(errorMessage)
        whenever(weatherService.getWeeklyForecast(any())).thenReturn(Observable.error(error))

        val observable = weatherRepository.getWeeklyForecast().test()

        observable.assertError(error)
    }
}
