package com.indramahkota.app.di

import android.content.Context
import com.indramahkota.common.utils.Constant
import com.indramahkota.data.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Provides
    @Singleton
    fun provideLocalDB(
        @ApplicationContext context: Context
    ) = MovieDatabase.invoke(context, Constant.DB_NAME)

    @Provides
    fun provideTourismDao(db: MovieDatabase) = db.movieDao()
}