package com.trendyol.celik.gokhun.platformlisting

import com.trendyol.celik.gokhun.common.extensions.ResourceReactiveExtensions.remote
import com.trendyol.celik.gokhun.data.platformlisting.repository.PlatformListingRepository
import com.trendyol.celik.gokhun.data.platformlisting.source.remote.model.PlatformListingResponse
import com.trendyol.celik.gokhun.domain.platformlisting.PlatformListingMapper
import com.trendyol.celik.gokhun.domain.platformlisting.PlatformListingUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test

class PlatformListingUseCaseTest {
    @MockK
    lateinit var platformListingRepository: PlatformListingRepository

    @MockK
    lateinit var platformListingMapper: PlatformListingMapper

    lateinit var platformListingUseCase: PlatformListingUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        platformListingUseCase = PlatformListingUseCase(
            platformListingRepository,
            platformListingMapper
        )
    }

    @Test
    fun `given successful response, when fetchPlatforms called, then should return game listing`() {
        // Given
        val response = PlatformListingResponse(
            null,null,null,null
        )

        every {
            platformListingRepository.fetchPlatforms()
        } answers { Observable.just(response).remote() }

        // When
        platformListingUseCase.fetchPlatforms()

        // Then
        verify { platformListingRepository.fetchPlatforms() }
    }



}
