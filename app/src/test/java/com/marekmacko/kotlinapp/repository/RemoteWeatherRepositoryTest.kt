package com.marekmacko.kotlinapp.repository

import com.marekmacko.kotlinapp.RxImmediateSchedulerRule
import com.marekmacko.kotlinapp.api.WeatherService
import com.marekmacko.kotlinapp.data.response.*
import com.marekmacko.kotlinapp.data.ui.ForecastShort
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

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
    fun getWeeklyForecastReturnData() {
        val weeklyForecast = getFakeData()
        whenever(weatherService.getWeeklyForecast(any())).thenReturn(Observable.just(weeklyForecast))

        val observableResult = remoteWeatherRepository.getWeeklyForecast()
        val testObserver = TestObserver<List<ForecastShort>>()
        observableResult.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        // TODO: assert response
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

    private fun getFakeData(): WeeklyForecast {
        val coordinates = Coordinates(12f, 23f)
        val city = City(1, "", coordinates, "Poland", 345)
        val temp = Temperature(12f, 12f, 12f, 12f, 12f, 12f)
        val weather = listOf(Weather(1, "Rain", "little rain", "10d"))
        val dailyForecast = DailyForecast(1234, temp, 12f, 12, weather, 12f, 12, 12, 12f)
        return WeeklyForecast(city, arrayListOf(dailyForecast))
    }
}