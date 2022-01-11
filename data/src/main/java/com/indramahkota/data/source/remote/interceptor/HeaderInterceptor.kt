package com.indramahkota.data.source.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("Connection", "close")
            .addHeader("Accept", "application/json")
            .build()
        return chain.proceed(newRequest)
    }
}