package com.trendyol.celik.gokhun.model.response.detail

import com.google.gson.annotations.SerializedName
import com.trendyol.celik.gokhun.model.response.list.PlatformDetail

data class PlatformInDetails(
    @SerializedName("platform")
    val platform: PlatformDetail?,

    @SerializedName("released_at")
    val releasedAt: String?,

    @SerializedName("requirements")
    val requirements: Requirements?

    )
