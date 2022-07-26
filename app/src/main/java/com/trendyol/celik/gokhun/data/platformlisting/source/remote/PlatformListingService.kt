package com.trendyol.celik.gokhun.data.platformlisting.source.remote

import com.trendyol.celik.gokhun.common.util.Constants
import com.trendyol.celik.gokhun.data.platformlisting.source.remote.model.PlatformListingResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface PlatformListingService {
    // to get list
    @GET("platforms/lists/parents?key=${Constants.API_KEY}")
    fun fetchPlatformList(
    ): Single<PlatformListingResponse>

}