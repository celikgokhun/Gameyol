package com.trendyol.celik.gokhun.ui.platformlisting

import com.trendyol.celik.gokhun.domain.model.PlatformListingPlatform

sealed class PlatformListingStatusViewState {
    object Loading : PlatformListingStatusViewState()
    object Empty : PlatformListingStatusViewState()
    data class Success(val platforms: PlatformListingPlatform?) : PlatformListingStatusViewState()
    data class Error(val throwable: Throwable) : PlatformListingStatusViewState()
}