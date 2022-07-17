package com.trendyol.celik.gokhun.data.gamelisting.source.remote.model

import com.google.gson.annotations.SerializedName

data class Years(
    @SerializedName("year")
    val year: Int?,

    @SerializedName("count")
    val count: Int?,

    @SerializedName("nofollow")
    val noFollow: Boolean?
)
