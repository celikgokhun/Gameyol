package com.trendyol.celik.gokhun.data.gamedetail.source.remote.model

import com.google.gson.annotations.SerializedName
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.PlatformDetail

data class PlatformInDetails(
    @SerializedName("platform")
    val platform: PlatformDetail?,

    @SerializedName("released_at")
    val releasedAt: String?,

    @SerializedName("requirements")
    val requirements: Requirements?

    )
