package com.marekmacko.kotlinapp.api

import com.marekmacko.kotlinapp.DataProviderSource
import com.marekmacko.kotlinapp.data.response.WeeklyForecast
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(DataProviderRunner::class)
class WeatherServiceTest {

    private val weatherService: WeatherService = mock()

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
        testObserver.assertValueAt(0, weeklyForecast)
    }
}
