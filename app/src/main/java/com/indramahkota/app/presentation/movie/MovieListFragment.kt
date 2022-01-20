package com.indramahkota.app.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.indramahkota.app.R
import com.indramahkota.app.databinding.FragmentListMovieBinding
import com.indramahkota.app.presentation.adapter.MovieAdapter
import com.indramahkota.app.viewmodel.MovieViewModel
import com.indramahkota.app.viewmodel.SharedViewModel
import com.indramahkota.common.base.BaseBindingFragment
import com.indramahkota.common.base.BaseModel
import com.indramahkota.common.base.LoadingModel
import com.indramahkota.domain.model.Movie
import com.indramahkota.domain.utils.UIState
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

class MovieListFragment : BaseBindingFragment() {
    private var _binding: FragmentListMovieBinding? = null
    private val binding get() = _binding!!

    private val args: MovieListFragmentArgs by navArgs()

    private val movieViewModel: MovieViewModel by activityViewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var movieAdapter: MovieAdapter

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentListMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUI(view: View, savedInstanceState: Bundle?) {
        sharedViewModel.isDetailPage.value = false

        initRecycleView()
        observeViewModel()
        initViewModel()
    }

    private fun initRecycleView() {
        movieAdapter = MovieAdapter {
            findNavController().navigate(R.id.detail_graph, Bundle().apply {
                putParcelable("movie", it)
            })

            Timber.d("Movie Clicked")
        }.also {
            it.setDatas(listOf())
        }

        with(binding.rvMovie) {
            adapter = movieAdapter
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            if (!args.isTv) {
                movieViewModel.movies.collectLatest {
                    handleResource(it)
                }
            } else {
                movieViewModel.tvs.collectLatest {
                    handleResource(it)
                }
            }
        }
    }

    private fun initViewModel() {
        if (!args.isTv) {
            movieViewModel.getMovies("Newest")
        } else {
            movieViewModel.getTvs("Newest")
        }
    }

    private fun handleResource(state: UIState<List<Movie>>) {
        when (state) {
            is UIState.Loading -> {
                for (i in 1..10)
                    movieAdapter.addItem(LoadingModel())
            }
            is UIState.Success -> {
                if (!state.data.isNullOrEmpty()) {
                    movieAdapter.setDatas(state.data as List<BaseModel>)
                } else {
                    movieAdapter.setEmpty("Data is empty")
                }
            }
            is UIState.Error -> movieAdapter.setEmpty(
                state.message ?: "Error: Something happen"
            )
            else -> {}
        }
    }

    override fun unbindFragment() {
        _binding = null
    }
}