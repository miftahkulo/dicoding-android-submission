package com.indramahkota.common.utils

object Constant {
    /** Constant **/
    const val DB_NAME = "moveous.db"
    const val API_URL = "api.themoviedb.org"
    const val BASE_URL = "https://$API_URL/3/discover/"
    // ref: https://www.themoviedb.org/talk/53c11d4ec3a3684cf4006400
    const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w342"

    /** Response Code **/
    const val NO_CONTENT = 204

    /** Recycle View Type **/
    const val TYPE_NORMAL = -1
    const val TYPE_LOADING = 0
    const val TYPE_EMPTY = 1

    /** Error Message **/
    const val UNKNOWN_ERROR = "Unknown error"
    const val DATE_PARSE_ERROR = "Date Parse Error"
    const val NO_INTERNET_ERROR = "No internet connection"

    /** **/
    const val LOREM_IPSUM = "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
}