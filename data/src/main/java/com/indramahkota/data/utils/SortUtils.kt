package com.indramahkota.data.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    private const val VOTE = "Vote"
    private const val NEWEST = "Newest"
    private const val RANDOM = "Random"
    private const val POPULARITY = "Popularity"

    fun getSortedQueryMovies(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movie where is_tv = 0 ")
        when (filter) {
            POPULARITY -> {
                simpleQuery.append("ORDER BY popularity DESC")
            }
            NEWEST -> {
                simpleQuery.append("ORDER BY release_date DESC")
            }
            VOTE -> {
                simpleQuery.append("ORDER BY vote_average DESC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryTvShows(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movie where is_tv = 1 ")
        when (filter) {
            POPULARITY -> {
                simpleQuery.append("ORDER BY popularity DESC")
            }
            NEWEST -> {
                simpleQuery.append("ORDER BY release_date DESC")
            }
            VOTE -> {
                simpleQuery.append("ORDER BY vote_average DESC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryFavoriteMovies(filter: String): SimpleSQLiteQuery {
        val simpleQuery =
            StringBuilder().append("SELECT * FROM movie where favorite = 1 and is_tv = 0 ")
        when (filter) {
            POPULARITY -> {
                simpleQuery.append("ORDER BY popularity DESC")
            }
            NEWEST -> {
                simpleQuery.append("ORDER BY release_date DESC")
            }
            VOTE -> {
                simpleQuery.append("ORDER BY vote_average DESC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryFavoriteTvShows(filter: String): SimpleSQLiteQuery {
        val simpleQuery =
            StringBuilder().append("SELECT * FROM movie where Favorite = 1 and is_tv = 1 ")
        when (filter) {
            POPULARITY -> {
                simpleQuery.append("ORDER BY popularity DESC")
            }
            NEWEST -> {
                simpleQuery.append("ORDER BY release_date DESC")
            }
            VOTE -> {
                simpleQuery.append("ORDER BY vote_average DESC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}