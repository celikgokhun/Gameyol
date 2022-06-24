package com.trendyol.celik.gokhun.model.response.list

import com.google.gson.annotations.SerializedName

data class ParentPlatform(
    @SerializedName("platform")
    val platform: ParentPlatformDetail?
)
