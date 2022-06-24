package com.trendyol.celik.gokhun.model.response.list

import com.google.gson.annotations.SerializedName

data class EsrbRating(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("slug")
    val slug: String?

)
