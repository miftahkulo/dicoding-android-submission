package com.indramahkota.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val results: List<MovieDto>
)

data class MovieDto(
    @SerializedName("overview")
    val overview: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("popularity")
    val popularity: Double,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String?,

    @SerializedName("vote_count")
    val voteCount: Int,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("first_air_date")
    val firstAirDate: String?,

    @SerializedName("name")
    val name: String?
)
