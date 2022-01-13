package com.indramahkota.data

import com.indramahkota.data.source.local.LocalDataSource
import com.indramahkota.data.source.remote.RemoteDataSource
import com.indramahkota.data.source.remote.network.ApiResponse
import com.indramahkota.data.source.remote.response.MovieResponse
import com.indramahkota.data.utils.AppExecutors
import com.indramahkota.data.utils.toListMovie
import com.indramahkota.data.utils.toListMovieEntity
import com.indramahkota.data.utils.toMovieEntity
import com.indramahkota.domain.model.Movie
import com.indramahkota.domain.repository.IMovieAppRepository
import com.indramahkota.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieAppRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieAppRepository {
    override fun getAllMovies(sort: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, MovieResponse>() {
            override suspend fun saveCallResult(data: MovieResponse) =
                localDataSource.insertMovies(data.results.toListMovieEntity())

            override fun shouldFetch(data: List<Movie>) =
                data.isEmpty()

            override fun loadFromDb(): Flow<List<Movie>> =
                localDataSource.getAllMovies(sort).map {
                    it.toListMovie()
                }

            override suspend fun createCall(): ApiResponse<MovieResponse> =
                remoteDataSource.getMovies()
        }.asFlow()

    override fun getAllTvShows(sort: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, MovieResponse>() {
            override suspend fun saveCallResult(data: MovieResponse) =
                localDataSource.insertMovies(data.results.toListMovieEntity(true))

            override fun shouldFetch(data: List<Movie>) =
                data.isEmpty()

            override fun loadFromDb(): Flow<List<Movie>> =
                localDataSource.getAllTvShows(sort).map {
                    it.toListMovie(true)
                }

            override suspend fun createCall(): ApiResponse<MovieResponse> =
                remoteDataSource.getTvShows()
        }.asFlow()

    override fun getFavoriteMovies(sort: String): Flow<List<Movie>> =
        localDataSource.getAllFavoriteMovies(sort).map {
            it.toListMovie()
        }

    override fun getFavoriteTvShows(sort: String): Flow<List<Movie>> =
        localDataSource.getAllFavoriteTvShows(sort).map {
            it.toListMovie(true)
        }

    override fun setMovieFavorite(movie: Movie, favorite: Boolean) {
        val movieEntity = movie.toMovieEntity(movie.isTvShows)
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movieEntity, favorite) }
    }
}