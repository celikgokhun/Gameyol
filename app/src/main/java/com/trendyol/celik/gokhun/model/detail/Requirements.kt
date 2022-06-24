package com.trendyol.celik.gokhun.model.detail

import com.google.gson.annotations.SerializedName

data class Requirements(
    @SerializedName("minimum")
    val releasedAt: String,

    @SerializedName("recommended")
    val recommended: String
)
