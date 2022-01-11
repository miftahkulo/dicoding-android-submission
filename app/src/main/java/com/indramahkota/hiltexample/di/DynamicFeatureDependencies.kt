package com.indramahkota.hiltexample.di

import com.indramahkota.domain.SampleRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DynamicFeatureDependencies {
    fun sampleRepository(): SampleRepository
}