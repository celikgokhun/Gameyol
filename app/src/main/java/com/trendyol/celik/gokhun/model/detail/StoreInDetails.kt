package com.trendyol.celik.gokhun.model.detail

import com.google.gson.annotations.SerializedName
import com.trendyol.celik.gokhun.model.list.StoreDetail

data class StoreInDetails(

    @SerializedName("id")
    val id: Int,

    @SerializedName("url")
    val url: String,

    @SerializedName("store")
    val store: StoreDetail,
)
