package com.trendyol.celik.gokhun.model.response.list

import com.google.gson.annotations.SerializedName

data class RequirementsEn(
    @SerializedName("minimum")
    val minimum: String?,

    @SerializedName("recommended")
    val recommended: String?
)
