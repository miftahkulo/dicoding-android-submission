package com.indramahkota.dynamic_feature.di

import android.content.Context
import com.indramahkota.dynamic_feature.DynamicFeatureFragment
import com.indramahkota.hiltexample.di.DynamicFeatureDependencies
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [DynamicFeatureDependencies::class],
    modules = [
        DynamicFeatureModule::class
    ]
)
interface DynamicFeatureComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            dependencies: DynamicFeatureDependencies
        ): DynamicFeatureComponent
    }

    fun inject(fragment: DynamicFeatureFragment)
}