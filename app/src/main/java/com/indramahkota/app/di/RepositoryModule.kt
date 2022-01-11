package com.indramahkota.app.di

import com.indramahkota.data.MovieAppRepositoryImpl
import com.indramahkota.data.source.local.LocalDataSource
import com.indramahkota.data.source.remote.RemoteDataSource
import com.indramahkota.data.utils.AppExecutors
import com.indramahkota.domain.repository.IMovieAppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        appExecutors: AppExecutors
    ): IMovieAppRepository = MovieAppRepositoryImpl(
        remoteDataSource,
        localDataSource,
        appExecutors
    )
}