package com.indramahkota.dynamic_feature

import androidx.lifecycle.ViewModel
import com.indramahkota.domain.SampleRepository
import javax.inject.Inject

class DynamicFeatureViewModel @Inject constructor(val repository: SampleRepository) : ViewModel() {
    fun getMessage() = repository.getMessage()
}