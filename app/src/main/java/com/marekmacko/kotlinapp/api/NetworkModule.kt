package com.marekmacko.kotlinapp.api

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
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
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .baseUrl(WeatherService.BASE_URL)
                    .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(responseCacheInterceptor: ResponseCacheInterceptor,
                            offlineCacheInterceptor: OfflineCacheInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                    .addNetworkInterceptor(responseCacheInterceptor)
                    .addInterceptor(offlineCacheInterceptor)
                    .cache(Cache(context.cacheDir, CACHE_SIZE_BYTES))
                    .build()

    @Provides
    @Singleton
    fun provideOfflineCacheInterceptor() = OfflineCacheInterceptor(context, CACHE_MAX_STALE_SECONDS)

    @Provides
    @Singleton
    fun provideResponseCacheInterceptor() = ResponseCacheInterceptor(CACHE_MAX_AGE_SECONDS)

    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): WeatherService =
            retrofit.create(WeatherService::class.java)
}
