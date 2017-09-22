package com.marekmacko.kotlinapp.api

import com.marekmacko.kotlinapp.DataProviderSource
import com.marekmacko.kotlinapp.RxImmediateSchedulerRule
import com.marekmacko.kotlinapp.data.response.WeeklyForecast
import com.nhaarman.mockito_kotlin.whenever
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@RunWith(DataProviderRunner::class)
class WeatherServiceTest {

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    val testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var weatherService: WeatherService

    @Test
    @UseDataProvider("getWeeklyForecast", location = arrayOf(DataProviderSource::class))
    fun getWeeklyForecast(weeklyForecast: WeeklyForecast) {
        val zipCode = "123"
        whenever(weatherService.getWeeklyForecast(zipCode)).thenReturn(Observable.just(weeklyForecast))

        val observableResult = weatherService.getWeeklyForecast(zipCode)
        val testObserver = TestObserver<WeeklyForecast>()
        observableResult.subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
    }
}
