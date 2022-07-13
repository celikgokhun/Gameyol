package com.trendyol.celik.gokhun

import com.trendyol.celik.gokhun.base.extensions.ResourceReactiveExtensions.remote
import com.trendyol.celik.gokhun.data.gamelisting.repository.GameListingRepository
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list.GameListingResponse
import com.trendyol.celik.gokhun.domain.gamelisting.GameListingMapper
import com.trendyol.celik.gokhun.domain.gamelisting.GameListingUseCase
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Test

class GameListingUseCaseTest {
    @MockK
    lateinit var gameListingRepository: GameListingRepository

    @MockK
    lateinit var gameListingMapper: GameListingMapper

    lateinit var gameListingUseCase: GameListingUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        gameListingUseCase = GameListingUseCase(
            gameListingRepository,
            gameListingMapper
        )
    }

    @Test
    fun `given successful response, when fetchGames called, then should return game listing`() {
        // Given
        val response = GameListingResponse(
            null,null,null,
            null,null,null,
            null,null,null,
            null,null,null,
            null
        )

        every {
            gameListingRepository.fetchGames()
        } answers { Observable.just(response).remote() }

        // When
        gameListingUseCase.fetchGames()

        // Then
        verify { gameListingRepository.fetchGames() }
    }

    @Test
    fun `given successful response, when fetchNextGames called, then should return game listing`() {
        // Given
        val next = "2"
        val response = GameListingResponse(
            null,null,null,
            null,null,null,
            null,null,null,
            null,null,null,
            null
        )

        every {
            gameListingRepository.fetchNextGames(next)
        } answers { Observable.just(response).remote() }

        // When
        gameListingUseCase.fetchNextGames(next)

        // Then
        verify { gameListingRepository.fetchNextGames(next) }
    }
}
