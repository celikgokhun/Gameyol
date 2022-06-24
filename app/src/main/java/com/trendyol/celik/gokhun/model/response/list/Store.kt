package com.trendyol.celik.gokhun.model.response.list

import com.google.gson.annotations.SerializedName

data class Store(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("store")
    val store: StoreDetail?
)
