package com.indramahkota.app.presentation.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.indramahkota.app.databinding.FragmentTvBinding
import com.indramahkota.app.presentation.adapter.MovieAdapter
import com.indramahkota.app.viewmodel.MovieViewModel
import com.indramahkota.common.base.BaseBindingFragment
import com.indramahkota.common.base.BaseModel
import com.indramahkota.common.base.LoadingModel
import com.indramahkota.domain.utils.Resource
import timber.log.Timber

class TvFragment : BaseBindingFragment() {
    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding!!

    private val movieViewModel: MovieViewModel by activityViewModels()

    private lateinit var movieAdapter: MovieAdapter

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUI(view: View, savedInstanceState: Bundle?) {
        initRecycleView()
        observeViewModel()
    }

    private fun initRecycleView() {
        movieAdapter = MovieAdapter().also {
            it.setDatas(listOf())
        }

        with(binding.rvTv) {
            adapter = movieAdapter
        }
    }

    private fun observeViewModel() {
        movieViewModel.getTv.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    Timber.d("Loading")
                    for (i in 1..10) {
                        movieAdapter.addItem(LoadingModel())
                    }
                }
                is Resource.Success -> {
                    Timber.d("Success")
                    movieAdapter.setDatas(it.data as List<BaseModel>)
                }
                is Resource.Error -> {
                    Timber.d("Error")
                }
            }
        }
    }

    override fun unbindFragment() {
        _binding = null
    }
}