package com.trendyol.celik.gokhun.data.gamelisting.source.remote.model

import com.google.gson.annotations.SerializedName

data class ParentPlatform(
    @SerializedName("platform")
    val platform: ParentPlatformDetail?
)
