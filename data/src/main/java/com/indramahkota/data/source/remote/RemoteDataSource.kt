package com.indramahkota.data.source.remote

import com.indramahkota.data.source.remote.network.ApiEmptyResponse
import com.indramahkota.data.source.remote.network.ApiResponse
import com.indramahkota.data.source.remote.network.ApiService
import com.indramahkota.data.source.remote.network.ApiSuccessResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class RemoteDataSource @Inject constructor(
    private val service: ApiService
) {
    private val dispatcher: CoroutineContext = Dispatchers.IO

    suspend fun getMovies() = withContext(dispatcher) {
        try {
            when (val response = ApiResponse.create(service.getMovies())) {
                is ApiSuccessResponse -> {
                    val success = response.body
                    if (success.results.isEmpty()) {
                        ApiEmptyResponse()
                    } else {
                        response
                    }
                }
                else -> response
            }
        } catch (e: Exception) {
            ApiResponse.create(e)
        }
    }

    suspend fun getTvShows() = withContext(dispatcher) {
        try {
            when (val response = ApiResponse.create(service.getTvShows())) {
                is ApiSuccessResponse -> {
                    val success = response.body
                    if (success.results.isEmpty()) {
                        ApiEmptyResponse()
                    } else {
                        response
                    }
                }
                else -> response
            }
        } catch (e: Exception) {
            ApiResponse.create(e)
        }
    }
}
