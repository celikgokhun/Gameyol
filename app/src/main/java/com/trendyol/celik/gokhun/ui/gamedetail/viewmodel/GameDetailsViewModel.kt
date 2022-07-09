package com.trendyol.celik.gokhun.ui.gamedetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trendyol.celik.gokhun.base.viewmodel.BaseViewModel
import com.trendyol.celik.gokhun.domain.gamedetail.GameDetailUseCase
import com.trendyol.celik.gokhun.domain.model.GameDetail
import com.trendyol.celik.gokhun.ui.gamedetail.GameDetailPageViewState
import com.trendyol.celik.gokhun.base.extensions.ResourceReactiveExtensions.subscribe
import com.trendyol.celik.gokhun.ui.gamedetail.GameDetailStatusViewState
import com.trendyol.celik.gokhun.ui.gamelisting.GameListingStatusViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val gameDetailUseCase: GameDetailUseCase
) : BaseViewModel() {

    private val pageViewStateLiveData: MutableLiveData<GameDetailPageViewState> = MutableLiveData()
    private val statusViewStateLiveData: MutableLiveData<GameDetailStatusViewState> = MutableLiveData()

    fun getPageViewStateLiveData(): LiveData<GameDetailPageViewState> = pageViewStateLiveData
    fun getStatusViewStateLiveData(): LiveData<GameDetailStatusViewState> = statusViewStateLiveData

    fun initializeViewModel(id: String) {
        if (pageViewStateLiveData.value == null) {
            fetchGameDetail(id)
        }
    }

    private fun fetchGameDetail(id: String) {
        gameDetailUseCase.fetchGameDetail(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                onSuccess ={
                    onGameDetailResponseReady(it)
                },
                onLoading = {
                    onGameDetailLoading()
                },
                onError = {
                    onGameDetailResponseFail(it)
                }
            )
    }

    private fun onGameDetailResponseReady(gameDetail: GameDetail) {
        if (gameDetail.equals(null)) {
            statusViewStateLiveData.value = GameDetailStatusViewState.Empty
        } else {
            statusViewStateLiveData.value = GameDetailStatusViewState.Success(gameDetail)
        }

    }

    private fun onGameDetailResponseFail(throwable: Throwable) {
        statusViewStateLiveData.value = GameDetailStatusViewState.Error(throwable)
    }

    private fun onGameDetailLoading() {
        statusViewStateLiveData.value = GameDetailStatusViewState.Loading
    }





}