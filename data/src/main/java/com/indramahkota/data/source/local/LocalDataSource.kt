package com.indramahkota.data.source.local

import com.indramahkota.data.source.local.entity.MovieEntity
import com.indramahkota.data.source.local.room.MovieDao
import com.indramahkota.data.utils.SortUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val mMovieDao: MovieDao
) {
    fun getAllMovies(sort: String): Flow<List<MovieEntity>> {
        val query = SortUtils.getSortedQueryMovies(sort)
        return mMovieDao.getMovies(query)
    }

    fun getAllTvShows(sort: String): Flow<List<MovieEntity>> {
        val query = SortUtils.getSortedQueryTvShows(sort)
        return mMovieDao.getTvShows(query)
    }

    fun getAllFavoriteMovies(sort: String): Flow<List<MovieEntity>> {
        val query = SortUtils.getSortedQueryFavoriteMovies(sort)
        return mMovieDao.getFavoriteMovies(query)
    }

    fun getAllFavoriteTvShows(sort: String): Flow<List<MovieEntity>> {
        val query = SortUtils.getSortedQueryFavoriteTvShows(sort)
        return mMovieDao.getFavoriteTvShows(query)
    }

    suspend fun insertMovies(movies: List<MovieEntity>) = mMovieDao.insertMovie(movies)

    fun setMovieFavorite(movie: MovieEntity, favorite: Boolean) {
        movie.favorite = favorite
        mMovieDao.updateFavoriteMovie(movie)
    }
}