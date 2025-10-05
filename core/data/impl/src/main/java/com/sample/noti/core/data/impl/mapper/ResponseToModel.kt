package com.sample.noti.core.data.impl.mapper

import com.sample.noti.core.model.CatFactsModel
import com.sample.noti.core.network.response.CatFactsResponse

internal fun CatFactsResponse.toModel(): CatFactsModel {
    return CatFactsModel(
        id = id,
        title = title,
        body = body,
        userId = userId
    )
}