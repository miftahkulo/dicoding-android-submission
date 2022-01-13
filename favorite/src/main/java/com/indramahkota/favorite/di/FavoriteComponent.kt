package com.indramahkota.favorite.di

import android.content.Context
import com.indramahkota.favorite.FavoriteFragment
import com.indramahkota.app.di.FavoriteDependencies
import com.indramahkota.favorite.FavoriteListFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [FavoriteDependencies::class],
    modules = [
        FavoriteModule::class
    ]
)
interface FavoriteComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            dependencies: FavoriteDependencies
        ): FavoriteComponent
    }

    fun inject(fragment: FavoriteFragment)
    fun inject(fragment: FavoriteListFragment)
}