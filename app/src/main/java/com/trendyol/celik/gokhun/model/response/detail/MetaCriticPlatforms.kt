package com.trendyol.celik.gokhun.model.response.detail

import com.google.gson.annotations.SerializedName

data class MetaCriticPlatforms(

    @SerializedName("metascore")
    val metaScore: Int?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("platform")
    val platform: PlatformInMetaCritic?

)
