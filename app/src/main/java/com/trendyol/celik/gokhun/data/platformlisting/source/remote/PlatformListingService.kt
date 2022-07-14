package com.trendyol.celik.gokhun.data.platformlisting.source.remote

import com.trendyol.celik.gokhun.data.platformlisting.source.remote.model.PlatformListingResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface PlatformListingService {
    // to get list
    @GET("platforms?key=f6c5c10dcd914fc4aa2b69b347ff02e7")
    fun fetchPlatformList(
    ): Single<PlatformListingResponse>

}