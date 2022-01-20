package com.indramahkota.app.fake

import com.indramahkota.domain.model.Movie
import com.indramahkota.domain.usecase.MovieAppUseCase
import com.indramahkota.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMovieUseCase : MovieAppUseCase {
    override fun getAllMovies(sort: String): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Success(FakeData.fakeListMovie()))
    }

    override fun getAllTvShows(sort: String): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Success(FakeData.fakeListTv()))
    }

    override fun getFavoriteMovies(sort: String): Flow<List<Movie>> = flow {
        emit(FakeData.fakeFavoriteListMovie())
    }

    override fun getFavoriteTvShows(sort: String): Flow<List<Movie>> = flow {
        emit(FakeData.fakeFavoriteListTv())
    }

    override fun setMovieFavorite(movie: Movie, state: Boolean) {}
}