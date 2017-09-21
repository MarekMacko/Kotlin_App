package com.marekmacko.kotlinapp.api

import android.content.Context
import com.marekmacko.kotlinapp.util.Utils
import okhttp3.Interceptor
import okhttp3.Response


class OfflineCacheInterceptor(private val context: Context,
                              cacheMaxStaleSeconds: Int) : Interceptor {

    private val headerName = "Cache-Control"
    private val headerValue = "public, only-if-cached, max-stale=$cacheMaxStaleSeconds"

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!Utils.isNetworkAvailable(context)) {
            request = request.newBuilder()
                    .header(headerName, headerValue)
                    .build()
        }
        return chain.proceed(request)
    }
}
