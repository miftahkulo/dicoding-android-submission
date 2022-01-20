package com.indramahkota.domain.utils

sealed class UIState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Empty<T> : UIState<T>()
    class Success<T>(data: T) : UIState<T>(data)
    class Loading<T>(data: T? = null) : UIState<T>(data)
    class Error<T>(message: String, data: T? = null) : UIState<T>(data, message)
}