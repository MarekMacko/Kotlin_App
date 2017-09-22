package com.marekmacko.kotlinapp.repository

import com.marekmacko.kotlinapp.DataProviderSource
import com.marekmacko.kotlinapp.RxImmediateSchedulerRule
import com.marekmacko.kotlinapp.api.WeatherService
import com.marekmacko.kotlinapp.data.response.WeeklyForecast
import com.marekmacko.kotlinapp.data.ui.ForecastShort
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
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

    @Mock lateinit var weatherService: WeatherService

    @InjectMocks lateinit var remoteWeatherRepository: RemoteWeatherRepository

    @Test
    @UseDataProvider("getWeeklyForecast", location = arrayOf(DataProviderSource::class))
    fun getWeeklyForecastReturnData(weeklyForecast: WeeklyForecast) {
        whenever(weatherService.getWeeklyForecast(any())).thenReturn(Observable.just(weeklyForecast))

        val observableResult = remoteWeatherRepository.getWeeklyForecast()
        val testObserver = TestObserver<List<ForecastShort>>()
        observableResult.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        testObserver.assertValue { // TODO
            it[0].description == DataProviderSource.EXPECTED_DESCRIPTION
        }
        testObserver.assertValue {
            it[0].minTemperature == DataProviderSource.EXPECTED_MIN_TEMPERATURE
        }
        testObserver.assertValue {
            it[0].maxTemperature == DataProviderSource.EXPECTED_MAX_TEMPERATURE
        }
    }

    @Test
    fun getWeeklyForecastReturnError() {
        val error = Throwable()
        whenever(weatherService.getWeeklyForecast(any())).thenReturn(Observable.error(error))

        val observableResult = remoteWeatherRepository.getWeeklyForecast()
        val testObserver = TestObserver<List<ForecastShort>>()
        observableResult.subscribe(testObserver)
        testObserver.assertError(error)
    }
}