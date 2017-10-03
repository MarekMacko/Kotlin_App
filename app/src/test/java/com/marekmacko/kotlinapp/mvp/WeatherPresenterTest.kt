package com.marekmacko.kotlinapp.mvp

import com.marekmacko.kotlinapp.MockData
import com.marekmacko.kotlinapp.RxImmediateSchedulerRule
import com.marekmacko.kotlinapp.data.ui.Forecast
import com.marekmacko.kotlinapp.repository.WeatherRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@RunWith(DataProviderRunner::class)
class WeatherPresenterTest {

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    val testSchedulerRule = RxImmediateSchedulerRule()

    private val view: WeatherMvp.View = mock()
    private val weatherRepository: WeatherRepository = mock()

    @InjectMocks
    lateinit var weatherPresenter: WeatherPresenter

    @Test
    @UseDataProvider("getWeeklyForecastArray", location = arrayOf(MockData::class))
    fun showLoadingIsCalledOnFetchForecast(forecastList: List<Forecast>) {
        whenever(weatherRepository.getWeeklyForecast()).thenReturn(Observable.just(forecastList))

        weatherPresenter.fetchForecast()

        verify(view).showLoading()
    }

    @Test
    @UseDataProvider("getWeeklyForecastArray", location = arrayOf(MockData::class))
    fun updateWeeklyForecastIsCalledWhenDataReturns(forecastList: List<Forecast>) {
        whenever(weatherRepository.getWeeklyForecast()).thenReturn(Observable.just(forecastList))

        weatherPresenter.fetchForecast()

        verify(view).updateWeeklyForecast(forecastList)
    }

    @Test
    fun showErrorIsCalledOnError() {
        val errorMessage = "Not found"
        whenever(weatherRepository.getWeeklyForecast()).thenReturn(Observable.error(Throwable(errorMessage)))

        weatherPresenter.fetchForecast()

        verify(view).showError()
    }

    @Test
    fun hideLoadingIsCalledOnComplete() {
        whenever(weatherRepository.getWeeklyForecast()).thenReturn(Observable.empty())

        weatherPresenter.fetchForecast()

        verify(view).hideLoading()
    }
}
