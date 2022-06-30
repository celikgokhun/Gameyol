package com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list

import com.google.gson.annotations.SerializedName

data class Platform(
    @SerializedName("platform")
    val platform: PlatformDetail?,

    @SerializedName("released_at")
    val releasedAt: String?,

    @SerializedName("requirements_en")
    val requirementsEn: RequirementsEn?,

    @SerializedName("requirements_ru")
    val requirementsRu: Any?,
)
