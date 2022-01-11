package com.indramahkota.data.source.remote.network

import com.indramahkota.common.utils.Constant.NO_CONTENT
import com.indramahkota.common.utils.Constant.UNKNOWN_ERROR
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

//Specific The Movie Database API V3 message property
const val THE_MOVIE_DB_V3_ERROR_MESSAGE_PROP = "status_message"

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(error.message ?: UNKNOWN_ERROR)
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == NO_CONTENT) {
                    ApiEmptyResponse()
                } else ApiSuccessResponse(body)
            } else {
                val msg = response.errorBody()?.string()
                val errorMessage = if (msg.isNullOrEmpty())
                    response.message()
                else {
                    try {
                        val message = JSONObject(msg)
                        message.getString(THE_MOVIE_DB_V3_ERROR_MESSAGE_PROP)
                    } catch (ex: JSONException) {
                        ex.message
                    }
                }
                ApiErrorResponse(errorMessage ?: UNKNOWN_ERROR)
            }
        }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()