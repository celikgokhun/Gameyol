package com.trendyol.celik.gokhun.domain.model.response.list

import com.google.gson.annotations.SerializedName

data class ShortScreenshots(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("image")
    val image: String?
)
