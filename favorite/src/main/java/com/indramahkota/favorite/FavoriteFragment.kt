package com.indramahkota.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.indramahkota.common.base.BaseBindingFragment
import com.indramahkota.domain.usecase.MovieAppUseCase
import com.indramahkota.favorite.databinding.FragmentFavoriteBinding
import timber.log.Timber
import javax.inject.Inject

class FavoriteFragment : BaseBindingFragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var useCase: MovieAppUseCase

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: FavoriteViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FavoriteViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUI(view: View, savedInstanceState: Bundle?) {
        Timber.d("Dynamic Feature by Direct Inject: $useCase")
        Timber.d("Dynamic Feature by ViewModel Inject")
    }

    override fun unbindFragment() {
        _binding = null
    }
}