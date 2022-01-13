package com.indramahkota.app.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.indramahkota.app.databinding.FragmentListMovieBinding
import com.indramahkota.app.presentation.adapter.MovieAdapter
import com.indramahkota.app.viewmodel.MovieViewModel
import com.indramahkota.common.base.BaseBindingFragment
import com.indramahkota.common.base.BaseModel
import com.indramahkota.common.base.LoadingModel
import com.indramahkota.domain.model.Movie
import com.indramahkota.domain.utils.Resource
import timber.log.Timber

class MovieListFragment : BaseBindingFragment() {
    private var _binding: FragmentListMovieBinding? = null
    private val binding get() = _binding!!

    private val args: MovieListFragmentArgs by navArgs()

    private val movieViewModel: MovieViewModel by activityViewModels()

    private lateinit var movieAdapter: MovieAdapter

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
        if (!args.isTv) {
            movieViewModel.getMovies.observe(this) {
                handleResource(it)
            }
        } else {
            movieViewModel.getTv.observe(this) {
                handleResource(it)
            }
        }
    }

    private fun handleResource(resource: Resource<List<Movie>>) {
        when (resource) {
            is Resource.Loading -> {
                Timber.d("Loading")
                for (i in 1..10) {
                    movieAdapter.addItem(LoadingModel())
                }
            }
            is Resource.Success -> {
                Timber.d("Success")
                movieAdapter.setDatas(resource.data as List<BaseModel>)
            }
            is Resource.Error -> {
                Timber.d("Error")
            }
        }
    }

    override fun unbindFragment() {
        _binding = null
    }
}