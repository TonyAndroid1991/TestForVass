package com.y4kuzabanzai.testforvass.repository

import com.y4kuzabanzai.testforvass.service.RetrofitInstance

class AppRepository {

    suspend fun getBrastlewarkPopulation() = RetrofitInstance.apiService.getBrastlewarkPopulation()


}