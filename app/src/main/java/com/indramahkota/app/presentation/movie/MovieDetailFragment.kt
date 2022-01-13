package com.indramahkota.app.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.indramahkota.app.databinding.VhDetailsBinding
import com.indramahkota.common.base.BaseBindingFragment

class MovieDetailFragment : BaseBindingFragment() {
    private var _binding: VhDetailsBinding? = null
    private val binding get() = _binding!!

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = VhDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUI(view: View, savedInstanceState: Bundle?) {

    }

    override fun unbindFragment() {
        _binding = null
    }
}