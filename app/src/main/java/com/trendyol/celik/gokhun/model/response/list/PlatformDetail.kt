package com.trendyol.celik.gokhun.model.response.list

import com.google.gson.annotations.SerializedName

data class PlatformDetail(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("slug")
    val slug: String?,

    @SerializedName("image")
    val image: Any?,

    @SerializedName("year_end")
    val yearEnd: Any?,

    @SerializedName("year_start")
    val yearStart: Any?,

    @SerializedName("games_count")
    val gamesCount: Int?,

    @SerializedName("image_background")
    val imageBackground: String?

    )
