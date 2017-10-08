package com.marekmacko.kotlinapp.mvp

import com.marekmacko.kotlinapp.repository.WeatherRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class WeatherPresenter(private val view: WeatherMvp.View,
                       private val weatherRepository: WeatherRepository) : WeatherMvp.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun fetchForecast() {
        view.hideError()
        view.showLoading()
        val disposable = weatherRepository.getWeeklyForecast()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnComplete { view.hideLoading() }
                .subscribe(
                        { view.updateWeeklyForecast(it) },
                        {
                            view.showError()
                            view.hideLoading()
                        }
                )
        compositeDisposable.add(disposable)
    }

    override fun cancelFetch() = compositeDisposable.clear()
}
