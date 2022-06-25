package com.trendyol.celik.gokhun.domain.model.response.list

import com.google.gson.annotations.SerializedName

data class Years(
    @SerializedName("year")
    val year: Int?,

    @SerializedName("count")
    val count: Int?,

    @SerializedName("nofollow")
    val noFollow: Boolean?
)
