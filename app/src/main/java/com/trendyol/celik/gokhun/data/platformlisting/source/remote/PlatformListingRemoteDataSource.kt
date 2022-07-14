package com.trendyol.celik.gokhun.data.platformlisting.source.remote

import com.trendyol.celik.gokhun.data.platformlisting.source.PlatformListingDataSource
import com.trendyol.celik.gokhun.data.platformlisting.source.remote.model.PlatformListingResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class PlatformListingRemoteDataSource @Inject constructor(
    private val platformListingService: PlatformListingService
) : PlatformListingDataSource.Remote {

    override fun fetchPlatforms(
    ): Observable<PlatformListingResponse> {
        return platformListingService
            .fetchPlatformList()
            .toObservable()
    }


}