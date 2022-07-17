package com.trendyol.celik.gokhun.data.gamelisting.source.remote.model

import com.google.gson.annotations.SerializedName

data class ShortScreenshots(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("image")
    val image: String?
)
