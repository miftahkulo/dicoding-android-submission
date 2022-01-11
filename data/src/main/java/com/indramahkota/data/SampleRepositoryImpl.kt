package com.indramahkota.data

import com.indramahkota.domain.SampleRepository

class SampleRepositoryImpl : SampleRepository {
    override fun getMessage() = "Hello Dagger Hilt!"
}
