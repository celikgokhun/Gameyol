package com.trendyol.celik.gokhun.data.platformlisting.repository

import com.trendyol.celik.gokhun.common.extensions.Resource
import com.trendyol.celik.gokhun.common.extensions.ResourceReactiveExtensions.remote
import com.trendyol.celik.gokhun.data.platformlisting.source.PlatformListingDataSource
import com.trendyol.celik.gokhun.data.platformlisting.source.remote.model.PlatformListingResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class PlatformListingRepository @Inject constructor(
    private val platformListingDataSource: PlatformListingDataSource.Remote
) {
    fun fetchPlatforms(
    ) : Observable<Resource<PlatformListingResponse>> {
        return platformListingDataSource
            .fetchPlatforms()
            .remote()
    }


}