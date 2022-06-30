package com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list

import com.google.gson.annotations.SerializedName

data class Filter (
    @SerializedName("years")
    val years: List<Year>?,
)