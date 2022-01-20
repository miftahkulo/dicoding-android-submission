package com.indramahkota.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indramahkota.app.di.IoDispatcher
import com.indramahkota.domain.model.Movie
import com.indramahkota.domain.usecase.MovieAppUseCase
import com.indramahkota.domain.utils.Resource
import com.indramahkota.domain.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val useCase: MovieAppUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _movies = MutableStateFlow<UIState<List<Movie>>>(UIState.Empty())
    val movies: StateFlow<UIState<List<Movie>>> = _movies

    fun getMovies(sort: String) {
        viewModelScope.launch(dispatcher) {
            useCase.getAllMovies(sort).collect {
                _movies.emit(
                    when (it) {
                        is Resource.Success -> {
                            UIState.Success(it.data!!)
                        }
                        is Resource.Error -> {
                            UIState.Error(it.message!!, it.data)
                        }
                        is Resource.Loading -> {
                            UIState.Loading(it.data)
                        }
                    }
                )
            }
        }
    }

    private val _tvs = MutableStateFlow<UIState<List<Movie>>>(UIState.Empty())
    val tvs: StateFlow<UIState<List<Movie>>> = _tvs

    fun getTvs(sort: String) {
        viewModelScope.launch(dispatcher) {
            useCase.getAllTvShows(sort).collect {
                _tvs.emit(
                    when (it) {
                        is Resource.Success -> {
                            UIState.Success(it.data!!)
                        }
                        is Resource.Error -> {
                            UIState.Error(it.message!!, it.data)
                        }
                        is Resource.Loading -> {
                            UIState.Loading(it.data)
                        }
                    }
                )
            }
        }
    }

    fun setFavorite(movie: Movie, state: Boolean) {
        useCase.setMovieFavorite(movie, state)
    }
}