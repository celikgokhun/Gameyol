package com.trendyol.celik.gokhun.domain.platformlisting

import com.trendyol.celik.gokhun.data.platformlisting.source.remote.model.PlatformListingPlatformResponse
import com.trendyol.celik.gokhun.data.platformlisting.source.remote.model.PlatformListingResponse
import com.trendyol.celik.gokhun.domain.model.Platform
import com.trendyol.celik.gokhun.domain.model.PlatformListingPlatform
import javax.inject.Inject

class PlatformListingMapper @Inject constructor() {

    fun mapFromResponse(response: PlatformListingResponse?): PlatformListingPlatform {
        return PlatformListingPlatform(
            platforms = mapPlatforms(response?.results),
            pagination = response?.next
        )
    }

    private fun mapPlatforms(platforms: List<PlatformListingPlatformResponse?>?): List<Platform> {
        return platforms?.mapNotNull {
            mapFromGameDetailResponseToPlatformDetail(it)
        }?.filter {
            true
        }.orEmpty()
    }


    private fun mapFromGameDetailResponseToPlatformDetail(response: PlatformListingPlatformResponse?): Platform {
        return Platform(
            id = response?.id.toString(),
            name = response?.name.orEmpty()
        )
    }

}