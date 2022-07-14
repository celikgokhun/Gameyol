package com.trendyol.celik.gokhun.data.gamesearch.source.remote.model

import com.google.gson.annotations.SerializedName

data class AddedByStatusGameSearch (
        @SerializedName("yet")
        val yet: Int?,

        @SerializedName("owned")
        val owned: Int?,
)