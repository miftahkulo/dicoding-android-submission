package com.indramahkota.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.indramahkota.domain.usecase.MovieAppUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(useCase: MovieAppUseCase) : ViewModel() {
    val getFavoriteMovies = useCase.getFavoriteMovies("Newest").asLiveData()
    val getFavoriteTv = useCase.getFavoriteTvShows("Newest").asLiveData()
}