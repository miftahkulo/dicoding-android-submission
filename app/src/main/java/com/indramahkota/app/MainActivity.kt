package com.indramahkota.app

import android.os.Bundle
import android.view.View
import com.indramahkota.app.databinding.ActivityMainBinding
import com.indramahkota.common.base.BaseBindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseBindingActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun setLayout(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun setupUI(savedInstanceState: Bundle?) {

    }
}