package com.indramahkota.dynamic_feature

import com.indramahkota.dynamic_feature.di.DaggerDynamicFeatureComponent
import com.indramahkota.hiltexample.di.DynamicFeatureDependencies
import dagger.hilt.android.EntryPointAccessors

internal fun DynamicFeatureFragment.inject() {
    DaggerDynamicFeatureComponent.factory().create(
        requireContext(),
        EntryPointAccessors.fromApplication(
            requireContext().applicationContext,
            DynamicFeatureDependencies::class.java
        )
    ).inject(this)
}