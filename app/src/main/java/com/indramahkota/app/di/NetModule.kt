package com.indramahkota.app.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.indramahkota.common.utils.Constant
import com.indramahkota.data.source.remote.interceptor.ConnectivityInterceptor
import com.indramahkota.data.source.remote.network.ApiService
import com.indramahkota.app.BuildConfig
import com.indramahkota.data.source.remote.interceptor.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetModule {
    @Provides
    @Singleton
    fun provideConnectivityInterceptor(
        @ApplicationContext context: Context
    ) = ConnectivityInterceptor(context)

    @Provides
    @Singleton
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Provides
    @Singleton
    fun provideApiKeyInterceptor() = ApiKeyInterceptor()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        connectivityInterceptor: ConnectivityInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        apiKeyInterceptor: ApiKeyInterceptor
    ) = OkHttpClient
        .Builder().apply {
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(connectivityInterceptor)
            addInterceptor(apiKeyInterceptor)
        }.build()

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient,
    ) = Retrofit
        .Builder().apply {
            baseUrl(Constant.BASE_URL)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create(gson))
        }.build()

    @Provides
    @Singleton
    fun provideMovieService(
        retrofit: Retrofit
    ) = retrofit.create(ApiService::class.java)
}