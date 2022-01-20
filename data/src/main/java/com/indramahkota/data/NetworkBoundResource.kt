package com.indramahkota.data

import com.indramahkota.data.source.remote.network.ApiEmptyResponse
import com.indramahkota.data.source.remote.network.ApiErrorResponse
import com.indramahkota.data.source.remote.network.ApiResponse
import com.indramahkota.data.source.remote.network.ApiSuccessResponse
import com.indramahkota.domain.utils.Resource
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {
    fun asFlow(): Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading(null))

        val dbValue = loadFromDb().first()
        if (shouldFetch(dbValue)) {
            emit(Resource.Loading(dbValue))
            when (val apiResponse = createCall()) {
                is ApiSuccessResponse -> {
                    saveCallResult(apiResponse.body!!)
                    emitAll(loadFromDb().map { Resource.Success(it) })
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    emitAll(loadFromDb().map { Resource.Error(apiResponse.errorMessage, it) })
                }
                is ApiEmptyResponse -> {
                    emitAll(loadFromDb().map { Resource.Success(it) })
                }
            }
        } else {
            emitAll(loadFromDb().map {
                Resource.Success(it)
            })
        }
    }

    protected abstract suspend fun saveCallResult(data: RequestType)

    protected abstract fun shouldFetch(data: ResultType): Boolean

    protected abstract fun loadFromDb(): Flow<ResultType>

    protected abstract suspend fun createCall(): ApiResponse<RequestType>

    protected open fun onFetchFailed() {}
}