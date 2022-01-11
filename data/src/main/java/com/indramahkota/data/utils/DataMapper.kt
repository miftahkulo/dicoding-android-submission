package com.indramahkota.data.utils

import com.indramahkota.common.utils.Constant.LOREM_IPSUM
import com.indramahkota.data.source.local.entity.MovieEntity
import com.indramahkota.data.source.remote.response.MovieDto
import com.indramahkota.domain.model.Movie

fun MovieDto.toMovieEntity(isTvShow: Boolean = false) = MovieEntity(
    id = id,
    title = title ?: (name ?: ""),
    voteCount = voteCount,
    posterPath = posterPath,
    overview = if (overview.isEmpty()) LOREM_IPSUM else overview,
    originalLanguage = originalLanguage,
    releaseDate = releaseDate ?: (firstAirDate ?: ""),
    popularity = popularity,
    voteAverage = voteAverage,
    isTvShows = isTvShow,
    favorite = false
)

@JvmName("toListMovieEntity1")
fun List<MovieDto>.toListMovieEntity(isTvShow: Boolean = false) =
    this.map {
        it.toMovieEntity(isTvShow)
    }

fun MovieEntity.toMovie(isTvShow: Boolean = false) = Movie(
    id = id,
    title = title,
    voteCount = voteCount,
    posterPath = posterPath,
    overview = overview,
    originalLanguage = originalLanguage,
    releaseDate = formatDateFromString(releaseDate, "yyyy-MM-dd", "EE, d MMM yyyy"),
    popularity = popularity,
    voteAverage = voteAverage,
    isTvShows = isTvShow,
    favorite = favorite
)

fun List<MovieEntity>.toListMovie(isTvShow: Boolean = false) =
    this.map {
        it.toMovie(isTvShow)
    }

fun Movie.toMovieEntity(isTvShow: Boolean = false) = MovieEntity(
    id = id,
    title = title,
    voteCount = voteCount,
    posterPath = posterPath,
    overview = overview,
    originalLanguage = originalLanguage,
    releaseDate = releaseDate,
    popularity = popularity,
    voteAverage = voteAverage,
    isTvShows = isTvShow,
    favorite = favorite
)