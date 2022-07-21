package com.trendyol.celik.gokhun.gamesearch

import com.trendyol.celik.gokhun.common.extensions.ResourceReactiveExtensions.remote
import com.trendyol.celik.gokhun.data.gamesearch.repository.GameSearchRepository
import com.trendyol.celik.gokhun.data.gamesearch.source.remote.model.GameSearchResponse
import com.trendyol.celik.gokhun.domain.gamesearch.GameSearchMapper
import com.trendyol.celik.gokhun.domain.gamesearch.GameSearchUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test

class GameSearchUseCaseTest {
    @MockK
    lateinit var gameSearchRepository: GameSearchRepository

    @MockK
    lateinit var gameSearchMapper: GameSearchMapper

    lateinit var gameSearchUseCase: GameSearchUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        gameSearchUseCase = GameSearchUseCase(
            gameSearchRepository,
            gameSearchMapper
        )
    }

    @Test
    fun `given successful response, when fetchSearchGame called, then should return game listing`() {
        // Given
        val response = GameSearchResponse(
            null,null,null,
            null,null
        )

        every {
            gameSearchRepository.fetchSearchGame("grand")
        } answers { Observable.just(response).remote() }

        // When
        gameSearchUseCase.fetchGameSearch("grand")

        // Then
        verify { gameSearchRepository.fetchSearchGame("grand") }
    }

    @Test
    fun `given successful response, when fetchSearchGameNext called, then should return game listing`() {
        // Given
        val next = "https://api.rawg.io/api/games?key=6c748fe3d2ca45b9a0a910aab0324c19&page=2&search=grand"
        val response = GameSearchResponse(
            null,null,null,
            null,null
        )

        every {
            gameSearchRepository.fetchSearchGameNext(next)
        } answers { Observable.just(response).remote() }

        // When
        gameSearchUseCase.fetchGameSearchNext(next)

        // Then
        verify { gameSearchRepository.fetchSearchGameNext(next) }
    }

}