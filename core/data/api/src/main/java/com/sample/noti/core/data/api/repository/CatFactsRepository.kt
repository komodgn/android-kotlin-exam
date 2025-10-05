package com.sample.noti.core.data.api.repository

import com.sample.noti.core.model.CatFactsModel

interface CatFactsRepository {
    suspend fun getCatFacts(): Result<CatFactsModel>
}
