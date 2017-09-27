package com.marekmacko.kotlinapp.mvp

import com.marekmacko.kotlinapp.DataProviderSource
import com.marekmacko.kotlinapp.RxImmediateSchedulerRule
import com.marekmacko.kotlinapp.data.ui.ForecastShort
import com.marekmacko.kotlinapp.repository.WeatherRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
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
    @UseDataProvider("getWeeklyForecastShort", location = arrayOf(DataProviderSource::class))
    fun showLoadingIsInvokedOnFetchForecast(forecastList: List<ForecastShort>) {
        whenever(weatherRepository.getWeeklyForecast()).thenReturn(Observable.just(forecastList))

        weatherPresenter.fetchForecast()

        verify(view, times(1)).showLoading()
    }

    @Test
    @UseDataProvider("getWeeklyForecastShort", location = arrayOf(DataProviderSource::class))
    fun updateWeeklyForecastIsInvokeWhenDataReturns(forecastList: List<ForecastShort>) {
        whenever(weatherRepository.getWeeklyForecast()).thenReturn(Observable.just(forecastList))

        weatherPresenter.fetchForecast()

        verify(view, times(1)).updateWeeklyForecast(forecastList)
    }

    @Test
    fun showErrorIsInvokedOnError() {
        val errorMessage = "Not found"
        whenever(weatherRepository.getWeeklyForecast()).thenReturn(Observable.error(Throwable(errorMessage)))

        weatherPresenter.fetchForecast()

        verify(view, times(1)).showError(errorMessage)
    }

    @Test
    fun hideLoadingIsInvokeOnComplete() {
        whenever(weatherRepository.getWeeklyForecast()).thenReturn(Observable.empty())

        weatherPresenter.fetchForecast()

        verify(view, times(1)).hideLoading()
    }
}
