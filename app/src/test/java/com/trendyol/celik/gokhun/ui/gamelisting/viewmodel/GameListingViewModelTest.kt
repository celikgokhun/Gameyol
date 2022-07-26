package com.trendyol.celik.gokhun.ui.gamelisting.viewmodel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.trendyol.celik.gokhun.common.extensions.Resource
import com.trendyol.celik.gokhun.common.extensions.Status
import com.trendyol.celik.gokhun.domain.gamelisting.GameListingUseCase
import com.trendyol.celik.gokhun.domain.gamesearch.GameSearchUseCase
import com.trendyol.celik.gokhun.domain.model.Game
import com.trendyol.celik.gokhun.domain.platformlisting.PlatformListingUseCase
import com.trendyol.celik.gokhun.util.RxSchedulerTestRule
import com.trendyol.celik.gokhun.util.test
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameListingViewModelTest {

    @MockK
    lateinit var gameListingUseCase: GameListingUseCase

    @MockK
    lateinit var gameSearchUseCase: GameSearchUseCase

    @MockK
    lateinit var platformListingUseCase: PlatformListingUseCase


    @Rule
    @JvmField
    var testSchedulerRule = RxSchedulerTestRule()


    lateinit var gameListingViewModel: GameListingViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        gameListingViewModel = GameListingViewModel(
            gameListingUseCase,
            gameSearchUseCase,
            platformListingUseCase
        )

    }

    /*
    @Test
    fun `given success state, when initializeViewModel called, then update live data for content status`() {

        // Given
        val mockObserver = gameListingViewModel.getStatusViewStateLiveData().test()
        val expectedValue = GameListingStatusViewState.Success(GameListingGame(listOf()))

        every {
            gameListingUseCase.fetchGames()
            /*
            gameListingUseCase.fetchNextGames("gfhgfh")

            platformListingUseCase.fetchPlatforms()

             */

        } returns Observable.just(Resource.Success(GameListingGame(listOf(createGame()))))


        // When
        gameListingViewModel.initializeViewModel()

        // Then
        Truth.assertThat(mockObserver.getValues().last()).isEqualTo(expectedValue)
    }



     */

    @Test
    fun `given error state, when initializeViewModel called, then update live data for error status`() {
        // Given
        val mockObserver = gameListingViewModel.getStatusViewStateLiveData().test()
        val throwableError = Throwable("errorMessage")

        every {
            gameListingUseCase
                .fetchGames() } returns
                Observable.just(Resource.Error(throwableError))

        // When
        gameListingViewModel.initializeViewModel()

        // Then
        Truth.assertThat(mockObserver.getValues().last()).isEqualTo(Status.Error(throwableError))
    }



    private fun createGame(): Game {
        return Game(
            id = "32432",
            name = "dasfas",
            backgroundImage = "https://video-content-img.dsmcdn.com/no-thumb.jpg"
        )
    }

}