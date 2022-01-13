package com.indramahkota.favorite

import com.indramahkota.favorite.di.DaggerFavoriteComponent
import com.indramahkota.app.di.FavoriteDependencies
import dagger.hilt.android.EntryPointAccessors

internal fun FavoriteFragment.inject() {
    DaggerFavoriteComponent.factory().create(
        requireContext(),
        EntryPointAccessors.fromApplication(
            requireContext().applicationContext,
            FavoriteDependencies::class.java
        )
    ).inject(this)
}

internal fun FavoriteListFragment.inject() {
    DaggerFavoriteComponent.factory().create(
        requireContext(),
        EntryPointAccessors.fromApplication(
            requireContext().applicationContext,
            FavoriteDependencies::class.java
        )
    ).inject(this)
}