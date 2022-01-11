package com.indramahkota.domain.usecase

import com.indramahkota.domain.model.Movie
import com.indramahkota.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieAppUseCase {
    fun getAllMovies(sort: String): Flow<Resource<List<Movie>>>

    fun getAllTvShows(sort: String): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(sort: String): Flow<List<Movie>>

    fun getFavoriteTvShows(sort: String): Flow<List<Movie>>

    fun setMovieFavorite(movie: Movie, state: Boolean)
}