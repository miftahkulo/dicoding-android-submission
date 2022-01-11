package com.indramahkota.app.di

import com.indramahkota.domain.usecase.MovieAppUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteDependencies {
    fun useCase(): MovieAppUseCase
}