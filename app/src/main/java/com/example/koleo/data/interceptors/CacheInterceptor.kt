package com.example.koleo.data.interceptors

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(CACHE_AGE_HOURS, TimeUnit.HOURS)
            .build()
        return response.newBuilder()
            .header(HEADER_CACHE_CONTROL, cacheControl.toString())
            .build()
    }

    companion object {
        private const val HEADER_CACHE_CONTROL = "Cache-Control"
        private const val CACHE_AGE_HOURS = 24
    }
}