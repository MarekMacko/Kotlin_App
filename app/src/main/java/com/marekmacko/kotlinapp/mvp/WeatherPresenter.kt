package com.marekmacko.kotlinapp.mvp

import com.marekmacko.kotlinapp.repository.WeatherRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class WeatherPresenter @Inject constructor(private val view: WeatherMvp.View,
                                           private val weatherRepository: WeatherRepository)
    : WeatherMvp.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun fetchForecast() {
        view.showLoading()
        val disposable = weatherRepository.getWeeklyForecast()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnComplete { view.hideLoading() }
                .subscribe(
                        { view.updateWeeklyForecast(it) },
                        {
                            view.showError(it.message ?: "No message provided")
                            view.hideLoading()
                        }
                )
        compositeDisposable.add(disposable)
    }

    override fun cancelFetch() {
        compositeDisposable.clear()
    }
}
