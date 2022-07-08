package com.trendyol.celik.gokhun.ui.gamelisting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trendyol.celik.gokhun.base.viewmodel.BaseViewModel
import com.trendyol.celik.gokhun.domain.gamelisting.GameListingUseCase
import com.trendyol.celik.gokhun.domain.model.GameListingGame
import com.trendyol.celik.gokhun.ui.gamelisting.GameListingPageViewState
import com.trendyol.celik.gokhun.ui.gamelisting.GameListingStatusViewState
import com.trendyol.celik.gokhun.base.extensions.ResourceReactiveExtensions.subscribe
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class GameListingViewModel @Inject constructor(
    private val gameListingUseCase: GameListingUseCase
) : BaseViewModel() {

    private val pageViewStateLiveData: MutableLiveData<GameListingPageViewState> = MutableLiveData()
    private val statusViewStateLiveData: MutableLiveData<GameListingStatusViewState> = MutableLiveData()

    fun getPageViewStateLiveData(): LiveData<GameListingPageViewState> = pageViewStateLiveData
    fun getStatusViewStateLiveData(): LiveData<GameListingStatusViewState> = statusViewStateLiveData

    fun initializeViewModel() {
        if (pageViewStateLiveData.value == null) {
            fetchGameList()
        }
    }

    private fun fetchGameList() {
        // onGameListingLoading(true)
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

    fun onNextPage() {
        pageViewStateLiveData.value?.getPageQueries?.let {
            fetchNextGameList(it)
        }
    }

    private fun onGameListingLoading() {
        statusViewStateLiveData.value = GameListingStatusViewState.Loading
    }
}
