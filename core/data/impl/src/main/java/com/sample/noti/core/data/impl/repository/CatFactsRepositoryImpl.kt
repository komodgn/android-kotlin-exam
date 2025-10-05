package com.sample.noti.core.data.impl.repository

import com.sample.noti.core.common.utils.runSuspendCatching
import com.sample.noti.core.data.api.repository.CatFactsRepository
import com.sample.noti.core.data.impl.mapper.toModel
import com.sample.noti.core.model.CatFactsModel
import com.sample.noti.core.network.service.CatFactsService
import javax.inject.Inject

internal class CatFactsRepositoryImpl @Inject constructor(
    private val service: CatFactsService,

) : CatFactsRepository {
    override suspend fun getCatFacts(): Result<CatFactsModel> = runSuspendCatching {
        service.getCatFacts().toModel()
    }
}