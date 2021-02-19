package com.y4kuzabanzai.testforvass.service

import com.y4kuzabanzai.testforvass.Models.BrastlewarkTown
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {


    @GET("/rrafols/mobile_test/master/data.json")
   suspend fun getBrastlewarkPopulation(): Response<BrastlewarkTown>
}