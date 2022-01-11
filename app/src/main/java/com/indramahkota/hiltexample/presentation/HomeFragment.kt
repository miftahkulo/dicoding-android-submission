package com.indramahkota.hiltexample.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.indramahkota.common.base.BaseBindingFragment
import com.indramahkota.domain.SampleRepository
import com.indramahkota.hiltexample.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseBindingFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var repository: SampleRepository

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUI(view: View, savedInstanceState: Bundle?) {
        Log.d("LOGGING", "Home: ${repository.getMessage()}")
    }

    override fun unbindFragment() {
        _binding = null
    }
}