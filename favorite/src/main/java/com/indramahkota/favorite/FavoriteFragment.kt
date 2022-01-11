package com.indramahkota.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.indramahkota.app.presentation.adapter.MovieAdapter
import com.indramahkota.common.base.BaseBindingFragment
import com.indramahkota.common.base.BaseModel
import com.indramahkota.favorite.databinding.FragmentFavoriteBinding
import javax.inject.Inject

class FavoriteFragment : BaseBindingFragment() {
    private var _binding: FragmentFavoriteBinding? = null
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
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
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

        with(binding.rvFavorite) {
            adapter = movieAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.getFavoriteMovies.observe(this) {
            movieAdapter.setDatas(it as List<BaseModel>)
        }
    }

    override fun unbindFragment() {
        _binding = null
    }
}