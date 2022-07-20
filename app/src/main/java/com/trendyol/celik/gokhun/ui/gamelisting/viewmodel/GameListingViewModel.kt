package com.trendyol.celik.gokhun.ui.gamelisting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trendyol.celik.gokhun.common.viewmodel.BaseViewModel
import com.trendyol.celik.gokhun.domain.gamelisting.GameListingUseCase
import com.trendyol.celik.gokhun.domain.model.GameListingGame
import com.trendyol.celik.gokhun.ui.gamelisting.GameListingPageViewState
import com.trendyol.celik.gokhun.ui.gamelisting.GameListingStatusViewState
import com.trendyol.celik.gokhun.common.extensions.ResourceReactiveExtensions.subscribe
import com.trendyol.celik.gokhun.domain.gamesearch.GameSearchUseCase
import com.trendyol.celik.gokhun.domain.model.PlatformListingPlatform
import com.trendyol.celik.gokhun.domain.platformlisting.PlatformListingUseCase
import com.trendyol.celik.gokhun.ui.platformlisting.PlatformListingPageViewState
import com.trendyol.celik.gokhun.ui.platformlisting.PlatformListingStatusViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class GameListingViewModel @Inject constructor(
    private val gameListingUseCase: GameListingUseCase,
    private val gameSearchUseCase: GameSearchUseCase,
    private val platformListingUseCase: PlatformListingUseCase
) : BaseViewModel() {

    private val pageViewStateLiveData: MutableLiveData<GameListingPageViewState> = MutableLiveData()
    private val statusViewStateLiveData: MutableLiveData<GameListingStatusViewState> = MutableLiveData()

    fun getStatusViewStateLiveData(): LiveData<GameListingStatusViewState> = statusViewStateLiveData

    private val pageViewPlatformStateLiveData: MutableLiveData<PlatformListingPageViewState> = MutableLiveData()
    private val statusViewPlatformStateLiveData: MutableLiveData<PlatformListingStatusViewState> = MutableLiveData()

    fun getStatusViewPlatformStateLiveData(): LiveData<PlatformListingStatusViewState> = statusViewPlatformStateLiveData

    fun initializeViewModel() {
        if (pageViewStateLiveData.value == null && pageViewPlatformStateLiveData.value == null) {
            fetchGameList()
            fetchPlatform()
        }
    }

    private fun fetchGameList() {
        gameListingUseCase.fetchGames()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                onSuccess = {
                    onGameListingResponseReady(it)
                },
                onLoading = {
                    onGameListingLoading()
                },
                onError = { throwable ->
                    onGameListingResponseFail(throwable)
                }
            )
            .also { disposable.add(it) }
    }

    private fun fetchNextGameList(next: String?) {
        gameListingUseCase.fetchNextGames(next)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                onSuccess = {
                    onGameListingResponseReady(it)
                },
                onLoading = {
                    onGameListingLoading()
                },
                onError = { throwable ->
                    onGameListingResponseFail(throwable)
                }
            )
            .also { disposable.add(it) }
    }

    private fun fetchGameSearch(search: String?) {
        println("here fetch")
        gameSearchUseCase.fetchGameSearch(search)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                onSuccess = {
                    onGameListingResponseReady(it)
                },
                onLoading = {
                    onGameListingLoading()
                },
                onError = { throwable ->
                    onGameListingResponseFail(throwable)
                }
            )
            .also { disposable.add(it)  }
    }

    private fun fetchPlatform() {
        platformListingUseCase.fetchPlatforms()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                onSuccess = {
                    onPlatformListingResponseReady(it)
                },
                onLoading = {
                    onPlatformListingLoading()
                },
                onError = { throwable ->
                    onPlatformListingResponseFail(throwable)
                }
            )
            .also { disposable.add(it) }
    }

    private fun fetchGameSearchNext(next: String?) {
        gameSearchUseCase.fetchGameSearchNext(next)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                onSuccess = {
                    onGameListingResponseReady(it)
                },
                onLoading = {
                    onGameListingLoading()
                },
                onError = { throwable ->
                    onGameListingResponseFail(throwable)
                }
            )
            .also { disposable.add(it) }
    }

    private fun onPlatformListingResponseReady(platformListing: PlatformListingPlatform) {
        if (platformListing.platforms.isEmpty()) {
            statusViewPlatformStateLiveData.value = PlatformListingStatusViewState.Empty
        } else {
            statusViewPlatformStateLiveData.value = PlatformListingStatusViewState.Success(platformListing)
        }
        pageViewPlatformStateLiveData.value = pageViewPlatformStateLiveData.value?.addNewPage(platformListing)
            ?: PlatformListingPageViewState(platformListing)
    }

    private fun onPlatformListingLoading() {
        statusViewPlatformStateLiveData.value = PlatformListingStatusViewState.Loading
    }

    private fun onPlatformListingResponseFail(throwable: Throwable) {
        statusViewPlatformStateLiveData.value = PlatformListingStatusViewState.Error(throwable)
    }

    private fun onGameListingResponseReady(gameListing: GameListingGame) {
        if (gameListing.games.isEmpty()) {
            statusViewStateLiveData.value = GameListingStatusViewState.Empty
        } else {
            statusViewStateLiveData.value = GameListingStatusViewState.Success(gameListing)
        }
        pageViewStateLiveData.value = pageViewStateLiveData.value?.addNewPage(gameListing)
            ?: GameListingPageViewState(gameListing)
    }

    private fun onGameListingResponseFail(throwable: Throwable) {
        statusViewStateLiveData.value = GameListingStatusViewState.Error(throwable)
    }

    private fun onGameListingLoading() {
        statusViewStateLiveData.value = GameListingStatusViewState.Loading
    }

    fun onNextGamePage() {
        pageViewStateLiveData.value?.getPageQueries?.let {
            fetchNextGameList(it)
        }
    }

    fun searchGame(search: String) {
        fetchGameSearch(search)
    }

    fun onNextSearchGame() {
        pageViewStateLiveData.value?.getPageQueries?.let {
            fetchGameSearchNext(it)
        }
    }
}
