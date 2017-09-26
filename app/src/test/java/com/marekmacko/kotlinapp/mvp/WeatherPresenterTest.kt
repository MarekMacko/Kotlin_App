package com.marekmacko.kotlinapp.mvp

import com.marekmacko.kotlinapp.DataProviderSource
import com.marekmacko.kotlinapp.data.ui.ForecastShort
import com.marekmacko.kotlinapp.repository.WeatherRepository
import com.nhaarman.mockito_kotlin.*
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import io.reactivex.disposables.Disposable
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

    private val view: WeatherMvp.View = mock()
    private val weatherRepository: WeatherRepository = mock()

    @InjectMocks
    lateinit var weatherPresenter: WeatherPresenter

    @Test
    @UseDataProvider("getWeeklyForecastShort", location = arrayOf(DataProviderSource::class))
    fun fetchForecastInvokeOnLoad(forecastList: List<ForecastShort>) {
        val disposableMock: Disposable = mock()
        doAnswer {
            val callback = it.arguments[0] as (List<ForecastShort>) -> Unit
            callback.invoke(forecastList)
            disposableMock
        }.whenever(weatherRepository).getWeeklyForecast(any(), any())

        weatherPresenter.fetchForecast()

        verify(view, times(1)).showLoading()
        verify(view, times(1)).updateWeeklyForecast(any())
        verify(view, times(1)).hideLoading()
    }

    @Test
    fun fetchForecastInvokeOnError() {
        val errorMessage = "Not found"
        val disposableMock: Disposable = mock()
        doAnswer {
            val callback = it.arguments[1] as (String) -> Unit
            callback.invoke(errorMessage)
            disposableMock
        }.whenever(weatherRepository).getWeeklyForecast(any(), any())

        weatherPresenter.fetchForecast()

        verify(view, times(1)).showLoading()
        verify(view, times(1)).showError(errorMessage)
        verify(view, times(1)).hideLoading()
    }
}
