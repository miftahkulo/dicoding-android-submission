package com.indramahkota.app.di

import com.indramahkota.domain.repository.IMovieAppRepository
import com.indramahkota.domain.usecase.MovieAppUseCase
import com.indramahkota.domain.usecase.MovieAppUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideMovieUseCase(repository: IMovieAppRepository): MovieAppUseCase =
        MovieAppUseCaseImpl(repository)
}