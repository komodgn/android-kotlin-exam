package com.sample.noti.core.data.impl.mapper

import com.sample.noti.core.model.CatFactsModel
import com.sample.noti.core.model.UserProfileModel
import com.sample.noti.core.network.response.CatFactsResponse
import com.sample.noti.core.network.response.UserProfileResponse

internal fun CatFactsResponse.toModel(): CatFactsModel {
    return CatFactsModel(
        id = id,
        title = title,
        body = body,
        userId = userId
    )
}

internal fun UserProfileResponse.toModel(): UserProfileModel {
    return UserProfileModel(
        id = id,
        email = email,
        nickname = nickname,
        provider = provider,
    )
}
