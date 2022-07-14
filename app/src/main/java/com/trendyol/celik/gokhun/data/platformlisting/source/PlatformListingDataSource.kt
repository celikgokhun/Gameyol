package com.trendyol.celik.gokhun.data.platformlisting.source

import com.trendyol.celik.gokhun.data.platformlisting.source.remote.model.PlatformListingResponse
import io.reactivex.rxjava3.core.Observable

interface PlatformListingDataSource {

    interface Remote {
            fun fetchPlatforms(
            ): Observable<PlatformListingResponse>
        }

}