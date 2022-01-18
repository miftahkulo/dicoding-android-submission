package com.indramahkota.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indramahkota.domain.model.Movie
import com.indramahkota.domain.usecase.MovieAppUseCase
import com.indramahkota.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val useCase: MovieAppUseCase
) : ViewModel() {
    private val _movies: MutableStateFlow<Resource<List<Movie>>?> = MutableStateFlow(null)
    val movies: StateFlow<Resource<List<Movie>>?> = _movies

    fun getMovies(sort: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getAllMovies(sort).collect {
                _movies.value = it
            }
        }
    }

    private val _tvs: MutableStateFlow<Resource<List<Movie>>?> = MutableStateFlow(null)
    val tvs: StateFlow<Resource<List<Movie>>?> = _tvs

    fun getTvs(sort: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getAllTvShows(sort).collect {
                _tvs.value = it
            }
        }
    }

    fun setFavorite(movie: Movie, state: Boolean) {
        useCase.setMovieFavorite(movie, state)
    }
}