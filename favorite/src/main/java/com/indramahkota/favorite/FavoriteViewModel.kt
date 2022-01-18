package com.indramahkota.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indramahkota.domain.model.Movie
import com.indramahkota.domain.usecase.MovieAppUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val useCase: MovieAppUseCase
) : ViewModel() {
    private val _movies: MutableStateFlow<List<Movie>?> = MutableStateFlow(null)
    val movies: StateFlow<List<Movie>?> = _movies

    fun getMovies(sort: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getFavoriteMovies(sort).collect {
                _movies.value = it
            }
        }
    }

    private val _tvs: MutableStateFlow<List<Movie>?> = MutableStateFlow(null)
    val tvs: StateFlow<List<Movie>?> = _tvs

    fun getTvs(sort: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getFavoriteTvShows(sort).collect {
                _tvs.value = it
            }
        }
    }
}