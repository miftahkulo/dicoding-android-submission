package com.indramahkota.data.source.remote.interceptor

import com.indramahkota.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ApiKeyInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val newRequest = original.newBuilder()
            .url(original.url.newBuilder().apply {
                addQueryParameter("api_key", BuildConfig.API_KEY)
            }.build()).build()
        return chain.proceed(newRequest)
    }
}