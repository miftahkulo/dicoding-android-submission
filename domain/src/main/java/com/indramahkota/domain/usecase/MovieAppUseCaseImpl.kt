package com.indramahkota.domain.usecase

import com.indramahkota.domain.model.Movie
import com.indramahkota.domain.repository.IMovieAppRepository
import com.indramahkota.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieAppUseCaseImpl @Inject constructor(
    private val iMovieAppRepository: IMovieAppRepository
) : MovieAppUseCase {
    override fun getAllMovies(sort: String): Flow<Resource<List<Movie>>> =
        iMovieAppRepository.getAllMovies(sort)

    override fun getAllTvShows(sort: String): Flow<Resource<List<Movie>>> =
        iMovieAppRepository.getAllTvShows(sort)

    override fun getFavoriteMovies(sort: String): Flow<List<Movie>> =
        iMovieAppRepository.getFavoriteMovies(sort)

    override fun getFavoriteTvShows(sort: String): Flow<List<Movie>> =
        iMovieAppRepository.getFavoriteTvShows(sort)

    override fun setMovieFavorite(movie: Movie, state: Boolean) =
        iMovieAppRepository.setMovieFavorite(movie, state)
}