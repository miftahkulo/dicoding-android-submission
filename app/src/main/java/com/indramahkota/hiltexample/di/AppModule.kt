package com.indramahkota.hiltexample.di

import com.indramahkota.data.SampleRepositoryImpl
import com.indramahkota.domain.SampleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSampleRepository(): SampleRepository = SampleRepositoryImpl()
}