package com.trendyol.celik.gokhun.data.gamedetail.source.remote.model

import com.google.gson.annotations.SerializedName

data class MetaCriticPlatforms(

    @SerializedName("metascore")
    val metaScore: Int?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("platform")
    val platform: PlatformInMetaCritic?

)
