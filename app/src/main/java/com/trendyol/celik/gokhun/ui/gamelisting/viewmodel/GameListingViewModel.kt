package com.trendyol.celik.gokhun.ui.gamelisting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trendyol.celik.gokhun.base.viewmodel.BaseViewModel
import com.trendyol.celik.gokhun.domain.gamelisting.GameListingUseCase
import com.trendyol.celik.gokhun.domain.model.Game
import com.trendyol.celik.gokhun.ui.gamelisting.GameListingPageViewState
import com.trendyol.celik.gokhun.ui.gamelisting.GameListingStatusViewState
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
                {
                    onGameListingResponseReady(it)
                },
                {
                    onGameListingResponseFail(it)
                }
            )
    }

    private fun onGameListingResponseReady(gameListing: List<Game>) {
        if (gameListing.isEmpty()) {
            statusViewStateLiveData.value = GameListingStatusViewState.Empty
        } else {
            statusViewStateLiveData.value = GameListingStatusViewState.Success(gameListing)
        }
        pageViewStateLiveData.value =  GameListingPageViewState(gameListing)
    }

    private fun onGameListingResponseFail(throwable: Throwable) {
        statusViewStateLiveData.value = GameListingStatusViewState.Error(throwable)
    }


    private fun onGameListingLoading(isLoading: Boolean) {
        if(isLoading){
            statusViewStateLiveData.value = GameListingStatusViewState.Loading
        }else{
            statusViewStateLiveData.value = GameListingStatusViewState.Empty
        }

    }
}






