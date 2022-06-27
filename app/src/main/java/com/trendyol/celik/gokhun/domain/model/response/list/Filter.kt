package com.trendyol.celik.gokhun.domain.model.response.list

import com.google.gson.annotations.SerializedName

data class Filter (
    @SerializedName("years")
    val years: List<Year>?,
)