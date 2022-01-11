package com.indramahkota.domain.model

import android.os.Parcelable
import com.indramahkota.common.base.BaseModel
import com.indramahkota.common.utils.Constant
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var id: Int,
    var title: String,
    var voteCount: Int,
    var posterPath: String,
    var overview: String,
    var originalLanguage: String,
    var releaseDate: String,
    var popularity: Double,
    var voteAverage: Double,
    var favorite: Boolean = false,
    var isTvShows: Boolean = false
) : Parcelable, BaseModel {
    override fun getType() = Constant.TYPE_NORMAL
}