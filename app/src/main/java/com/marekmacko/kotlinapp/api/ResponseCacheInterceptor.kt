package com.marekmacko.kotlinapp.api

import okhttp3.Interceptor
import okhttp3.Response


class ResponseCacheInterceptor(cacheMaxAgeSeconds: Int) : Interceptor {

    private val headerValue = "public, max-age=$cacheMaxAgeSeconds"
    private val headerName = "Cache-Control"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        return response.newBuilder()
                .header(headerName, headerValue)
                .build()
    }
}