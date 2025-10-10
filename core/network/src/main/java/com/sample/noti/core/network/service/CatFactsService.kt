package com.sample.noti.core.network.service

import com.sample.noti.core.network.response.CatFactsResponse
import retrofit2.http.GET

interface CatFactsService {
    @GET("/posts/1")
    suspend fun getCatFacts(): CatFactsResponse
}