package com.indramahkota.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.indramahkota.app.R
import com.indramahkota.app.databinding.FragmentListMovieBinding
import com.indramahkota.app.presentation.adapter.MovieAdapter
import com.indramahkota.common.base.BaseBindingFragment
import com.indramahkota.common.base.BaseModel
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
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
            arguments?.takeIf { it.containsKey(IS_TV_ARG) }?.apply {
                if (!getBoolean(IS_TV_ARG)) {
                    viewModel.movies.collectLatest {
                        if (it.isNotEmpty()) {
                            movieAdapter.setDatas(it as List<BaseModel>)
                        } else {
                            movieAdapter.setEmpty("Data is empty")
                        }
                    }
                } else {
                    viewModel.tvs.collectLatest {
                        if (it.isNotEmpty()) {
                            movieAdapter.setDatas(it as List<BaseModel>)
                        } else {
                            movieAdapter.setEmpty("Data is empty")
                        }
                    }
                }
            }
        }
    }

    private fun initViewModel() {
        arguments?.takeIf { it.containsKey(IS_TV_ARG) }?.apply {
            if (!getBoolean(IS_TV_ARG)) {
                viewModel.getMovies("Newest")
            } else {
                viewModel.getTvs("Newest")
            }
        }
    }

    override fun unbindFragment() {
        _binding = null
    }
}