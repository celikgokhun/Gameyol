package com.trendyol.celik.gokhun.data.gamesearch.source.remote.model

import com.google.gson.annotations.SerializedName
import com.google.gson.internal.LinkedTreeMap

data class PlatformGameSearch(
    @SerializedName("platform")
    val platform: LinkedTreeMap<String, String>
)
