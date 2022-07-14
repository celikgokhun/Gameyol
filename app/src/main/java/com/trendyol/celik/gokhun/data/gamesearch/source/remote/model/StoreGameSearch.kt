package com.trendyol.celik.gokhun.data.gamesearch.source.remote.model

import com.google.gson.annotations.SerializedName

data class StoreGameSearch(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("slug")
    val slug: String?
)
