package com.indramahkota.data.source.remote.network

import com.indramahkota.data.source.remote.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("movie")
    suspend fun getMovies(): Response<MovieResponse>

    @GET("tv")
    suspend fun getTvShows(): Response<MovieResponse>
}