package com.marekmacko.kotlinapp.api

import android.content.Context
import com.marekmacko.kotlinapp.util.Utils
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
internal class NetworkModule(private val context: Context) {

    companion object {
        private const val CACHE_SIZE_MB: Long = 5
        private const val CACHE_SIZE_BYTES: Long = CACHE_SIZE_MB * 1024 * 1024
        private const val CACHE_MAX_AGE_SECONDS = 60
        private const val CACHE_MAX_STALE_SECONDS = 2419200
    }

    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
            Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClient())
                    .baseUrl(WeatherService.BASE_URL)
                    .build()

    // TODO: should be moved to parametrized class or be provided by NetworkModule?
    private fun getOkHttpClient() =
            OkHttpClient.Builder()
                    .addNetworkInterceptor(getResponseCacheInterceptor())
                    .addInterceptor(getOfflineResponseCacheInterceptor())
                    .cache(Cache(context.cacheDir, CACHE_SIZE_BYTES))
                    .build()

    // TODO: should be moved to parametrized class?
    private fun getOfflineResponseCacheInterceptor(): Interceptor = Interceptor {
        var request = it.request()
        if (!Utils.isNetworkAvailable(context)) {
            request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$CACHE_MAX_STALE_SECONDS")
                    .build()
        }
        it.proceed(request)
    }

    // TODO: should be moved to parametrized class?
    private fun getResponseCacheInterceptor(): Interceptor = Interceptor {
        val response = it.proceed(it.request())
        response.newBuilder()
                .header("Cache-Control", "public, max-age=$CACHE_MAX_AGE_SECONDS")
                .build()
    }

    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): WeatherService =
            retrofit.create(WeatherService::class.java)
}