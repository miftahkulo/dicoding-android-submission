package com.indramahkota.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.indramahkota.domain.model.Movie
import com.indramahkota.domain.usecase.MovieAppUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val useCase: MovieAppUseCase
) : ViewModel() {
    val getMovies = useCase.getAllMovies("Newest").asLiveData()
    val getTv = useCase.getAllTvShows("Newest").asLiveData()

    fun setFavorite(movie: Movie, state: Boolean) {
        useCase.setMovieFavorite(movie, state)
    }
}