package com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list

import com.google.gson.annotations.SerializedName

data class Year(
        @SerializedName("from")
        val from: Int?,

        @SerializedName("to")
        val to: Int?,

        @SerializedName("filter")
        val filter: String?,

        @SerializedName("decade")
        val decade: Int?,

        @SerializedName("years")
        val years: List<Years>?,

        @SerializedName("nofollow")
        val noFollow: Boolean?,

        @SerializedName("count")
        val count: Int?
)