package com.indramahkota.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.indramahkota.app.databinding.FragmentListMovieBinding
import com.indramahkota.app.presentation.adapter.MovieAdapter
import com.indramahkota.common.base.BaseBindingFragment
import com.indramahkota.common.base.BaseModel
import com.indramahkota.common.base.EmptyModel
import javax.inject.Inject

const val IS_TV_ARG = "isTv"

class FavoriteListFragment : BaseBindingFragment() {
    private var _binding: FragmentListMovieBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: FavoriteViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FavoriteViewModel::class.java]
    }

    private lateinit var movieAdapter: MovieAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentListMovieBinding.inflate(inflater, container, false)
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

        with(binding.rvMovie) {
            adapter = movieAdapter
        }
    }

    private fun observeViewModel() {
        arguments?.takeIf { it.containsKey(IS_TV_ARG) }?.apply {
            if (!getBoolean(IS_TV_ARG)) {
                viewModel.getFavoriteMovies.observe(this@FavoriteListFragment) {
                    if (it.isNotEmpty()) {
                        movieAdapter.setDatas(it as List<BaseModel>)
                    } else {
                        movieAdapter.setEmpty("Data is empty")
                    }
                }
            } else {
                viewModel.getFavoriteTv.observe(this@FavoriteListFragment) {
                    if (it.isNotEmpty()) {
                        movieAdapter.setDatas(it as List<BaseModel>)
                    } else {
                        movieAdapter.setEmpty("Data is empty")
                    }
                }
            }
        }
    }

    override fun unbindFragment() {
        _binding = null
    }
}