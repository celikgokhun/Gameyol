package com.trendyol.celik.gokhun.data.platformlisting.source.remote.model

import com.google.gson.annotations.SerializedName

data class PlatformGame(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("slug")
    val slug: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("added")
    val added: Int?
)
