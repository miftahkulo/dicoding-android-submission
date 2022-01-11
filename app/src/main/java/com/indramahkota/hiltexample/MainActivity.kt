package com.indramahkota.hiltexample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.indramahkota.common.base.BaseBindingActivity
import com.indramahkota.domain.SampleRepository
import com.indramahkota.hiltexample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseBindingActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var repository: SampleRepository

    private lateinit var navController: NavController

    override fun setLayout(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        Log.d("LOGGING", "Main: ${repository.getMessage()}")

        with(binding) {
            val navHostFragment = supportFragmentManager.findFragmentById(
                navHostContainer.id
            ) as NavHostFragment

            navController = navHostFragment.navController

            btnAppFeature.setOnClickListener {
                navController.navigate(R.id.homeFragment)
            }

            btnRegularFeature.setOnClickListener {
                navController.navigate(R.id.regularFeatureFragment)
            }

            btnDynamicFeature.setOnClickListener {
                navController.navigate(R.id.dynamicFeatureFragment)
            }
        }
    }
}