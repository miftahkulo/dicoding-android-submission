package com.indramahkota.data

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
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

    @WorkerThread
    protected abstract suspend fun saveCallResult(data: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType): Boolean

    @MainThread
    protected abstract fun loadFromDb(): Flow<ResultType>

    @MainThread
    protected abstract suspend fun createCall(): ApiResponse<RequestType>

    protected open fun onFetchFailed() {}
}