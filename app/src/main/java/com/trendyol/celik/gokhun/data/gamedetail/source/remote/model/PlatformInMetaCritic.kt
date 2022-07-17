package com.trendyol.celik.gokhun.data.gamedetail.source.remote.model

import com.google.gson.annotations.SerializedName

data class PlatformInMetaCritic(

    @SerializedName("platform")
    val platform: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("slug")
    val slug: String?
)
