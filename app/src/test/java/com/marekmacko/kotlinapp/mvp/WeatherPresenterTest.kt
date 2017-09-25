package com.marekmacko.kotlinapp.mvp

import com.marekmacko.kotlinapp.repository.WeatherRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.disposables.Disposable
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class WeatherPresenterTest {

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var view: WeatherMvp.View

    @Mock
    lateinit var weatherRepository: WeatherRepository

    @InjectMocks
    lateinit var weatherPresenter: WeatherPresenter

    @Test
    fun fetchForecast() {
        val disposableMock = Mockito.mock(Disposable::class.java)
        whenever(weatherRepository.getWeeklyForecast(any(), any())).thenReturn(disposableMock)

        weatherPresenter.fetchForecast()

        // TODO: not finished
//        verify(view.hideLoading())
//        verify(view.updateWeeklyForecast(any()))
    }
}