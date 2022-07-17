package com.trendyol.celik.gokhun.data.gamedetail.source.remote.model

import com.google.gson.annotations.SerializedName
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.StoreDetail

data class StoreInDetails(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("store")
    val store: StoreDetail?
)
