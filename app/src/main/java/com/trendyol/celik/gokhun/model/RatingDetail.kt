package com.trendyol.celik.gokhun.model

import com.google.gson.annotations.SerializedName

data class RatingDetail(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("count")
    val count: Int,

    @SerializedName("percent")
    val percent: Double,
)
