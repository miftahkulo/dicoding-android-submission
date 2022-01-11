package com.indramahkota.app.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/* ViewModel Map needed by ViewModelFactory */
@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}