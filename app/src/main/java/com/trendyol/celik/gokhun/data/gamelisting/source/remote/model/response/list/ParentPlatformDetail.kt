package com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list

import com.google.gson.annotations.SerializedName

data class ParentPlatformDetail(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("slug")
    val slug: String?
)
