package com.trendyol.celik.gokhun.model

import com.google.gson.annotations.SerializedName

data class ParentPlatformDetail(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("slug")
    val slug: String,
)
