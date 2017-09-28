package com.marekmacko.kotlinapp.api

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {

    companion object {
        private const val CACHE_SIZE_MB: Long = 5
        private const val CACHE_SIZE_BYTES: Long = CACHE_SIZE_MB * 1024 * 1024
        private const val CACHE_MAX_AGE_SECONDS = 60
        private const val CACHE_MAX_STALE_SECONDS = 2419200
    }

    @Provides
    fun provideWeatherService(retrofit: Retrofit): WeatherService =
            retrofit.create(WeatherService::class.java)

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .baseUrl(WeatherService.BASE_URL)
                    .build()

    @Provides
    fun provideOkHttpClient(context: Context, responseCacheInterceptor: ResponseCacheInterceptor,
                            offlineCacheInterceptor: OfflineCacheInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                    .addNetworkInterceptor(responseCacheInterceptor)
                    .addInterceptor(offlineCacheInterceptor)
                    .cache(Cache(context.cacheDir, CACHE_SIZE_BYTES))
                    .build()

    @Provides
    fun provideOfflineCacheInterceptor(context: Context)
            = OfflineCacheInterceptor(context, CACHE_MAX_STALE_SECONDS)

    @Provides
    fun provideResponseCacheInterceptor() = ResponseCacheInterceptor(CACHE_MAX_AGE_SECONDS)
}
