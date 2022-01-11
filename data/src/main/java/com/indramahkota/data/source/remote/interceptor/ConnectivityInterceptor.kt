package com.indramahkota.data.source.remote.interceptor

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.indramahkota.common.utils.Constant.NO_INTERNET_ERROR
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(private val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline) throw IOException(NO_INTERNET_ERROR)
        return chain.proceed(chain.request())
    }

    private val isOnline: Boolean
        /**
         * Must have ACCESS_NETWORK_STATE permission
         * **/
        @SuppressLint("MissingPermission")
        get() {
            var result = false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities = connectivityManager.getNetworkCapabilities(
                connectivityManager.activeNetwork
            )
            when {
                capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) -> {
                    result = true
                }
            }
            return result
        }
}