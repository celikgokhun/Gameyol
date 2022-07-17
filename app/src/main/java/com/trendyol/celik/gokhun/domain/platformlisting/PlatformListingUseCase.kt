package com.trendyol.celik.gokhun.domain.platformlisting

import com.trendyol.celik.gokhun.common.extensions.Resource
import com.trendyol.celik.gokhun.common.extensions.mapOnData
import com.trendyol.celik.gokhun.data.platformlisting.repository.PlatformListingRepository
import com.trendyol.celik.gokhun.domain.model.PlatformListingPlatform
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class PlatformListingUseCase @Inject constructor(
    private val platformListingRepository: PlatformListingRepository,
    private val platformListingMapper: PlatformListingMapper
) {
    fun fetchPlatforms(): Observable<Resource<PlatformListingPlatform>> {
        return platformListingRepository
            .fetchPlatforms()
            .mapOnData {
                platformListingMapper.mapFromResponse(it)
            }
    }
}