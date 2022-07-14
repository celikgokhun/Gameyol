package com.trendyol.celik.gokhun.data.platformlisting.source.remote.model

import com.google.gson.annotations.SerializedName

data class PlatformListingResponse(
    @SerializedName("count")
    val count: Int?,

    @SerializedName("next")
    val next: String?,

    @SerializedName("previous")
    val previous: String?,

    @SerializedName("results")
    val results: List<PlatformListingPlatformResponse?>?
)