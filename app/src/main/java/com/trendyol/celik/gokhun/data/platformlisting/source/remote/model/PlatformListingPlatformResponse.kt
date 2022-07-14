package com.trendyol.celik.gokhun.data.platformlisting.source.remote.model

import com.google.gson.annotations.SerializedName

data class PlatformListingPlatformResponse(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("slug")
    val slug: String?,

    @SerializedName("games_count")
    val gamesCount: Int?,

    @SerializedName("image_background")
    val imageBackground: String?,

    @SerializedName("image")
    val image: String?,

    @SerializedName("year_start")
    val yearStart: Int?,

    @SerializedName("year_end")
    val yearEnd: Int?,

    @SerializedName("games")
    val games: List<PlatformGame?>?

)
