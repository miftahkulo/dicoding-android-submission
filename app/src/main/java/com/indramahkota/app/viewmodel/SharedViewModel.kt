package com.indramahkota.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.indramahkota.app.R
import com.indramahkota.domain.model.Movie

class SharedViewModel : ViewModel() {
    var previousHomeStateFragment = R.id.movie_graph
    val isDetailPage = MutableLiveData(false)
    val isMovieFavorite = MutableLiveData<Movie?>()
}