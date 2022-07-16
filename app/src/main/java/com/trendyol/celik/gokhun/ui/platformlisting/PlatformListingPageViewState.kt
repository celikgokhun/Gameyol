package com.trendyol.celik.gokhun.ui.platformlisting

import com.trendyol.celik.gokhun.domain.model.PlatformListingPlatform

data class PlatformListingPageViewState(
    val platformListing: PlatformListingPlatform
) {
    fun addNewPage(newPage: PlatformListingPlatform): PlatformListingPageViewState {
        val newList = platformListing.platforms + newPage.platforms
        return copy(platformListing = platformListing.copy(platforms = newList, pagination = newPage.pagination))
    }

}