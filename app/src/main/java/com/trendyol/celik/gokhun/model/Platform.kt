package com.trendyol.celik.gokhun.model

import com.google.gson.annotations.SerializedName

data class Platform(
    @SerializedName("platform")
    val platform: List<PlatformDetail>,

    @SerializedName("released_at")
    val releasedAt: String,

    @SerializedName("requirements_en")
    val requirementsEn: RequirementsEn,

    @SerializedName("requirements_ru")
    val requirementsRu: Any,
)
