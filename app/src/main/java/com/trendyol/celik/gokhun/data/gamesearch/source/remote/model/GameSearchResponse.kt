package com.trendyol.celik.gokhun.data.gamesearch.source.remote.model

import com.google.gson.annotations.SerializedName

data class GameSearchResponse(
    @SerializedName("count")
    val count: Int?,

    @SerializedName("next")
    val next: String?,

    @SerializedName("previous")
    val previous: Any?,

    @SerializedName("results")
    val results: List<GameSearchGameResponse?>?,

    @SerializedName("user_platforms")
    val userPlatforms: Boolean?
)