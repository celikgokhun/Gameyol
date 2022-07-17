package com.trendyol.celik.gokhun

import com.trendyol.celik.gokhun.common.extensions.ResourceReactiveExtensions.remote
import com.trendyol.celik.gokhun.data.gamedetail.repository.GameDetailRepository
import com.trendyol.celik.gokhun.data.gamedetail.source.remote.model.GameDetailResponse
import com.trendyol.celik.gokhun.domain.gamedetail.GameDetailMapper
import com.trendyol.celik.gokhun.domain.gamedetail.GameDetailUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test

class GameDetailUseCaseTest {
    @MockK
    lateinit var gameDetailRepository: GameDetailRepository

    @MockK
    lateinit var gameDetailMapper: GameDetailMapper

    lateinit var gameDetailUseCase: GameDetailUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        gameDetailUseCase = GameDetailUseCase(
            gameDetailRepository,
            gameDetailMapper
        )
    }

    @Test
    fun `given successful response, when fetchGames called, then should return game listing`() {
        // Given
        val response = GameDetailResponse(
            null,null,null, null,null,null,
            null,null,null, null,null,null,
            null,null, null, null,null, null,
            null,null, null, null,null, null,
            null,null, null, null,null, null,
            null,null, null, null,null,
            null, null,null, null, null,
            null, null, null,null, null,
            null,null, null, null,null, null,
            null,null, null
        )

        every {
            gameDetailRepository.fetchGameDetail("3213")
        } answers { Observable.just(response).remote() }

        // When
        gameDetailUseCase.fetchGameDetail("3213")

        // Then
        verify { gameDetailRepository.fetchGameDetail("3213") }
    }

}