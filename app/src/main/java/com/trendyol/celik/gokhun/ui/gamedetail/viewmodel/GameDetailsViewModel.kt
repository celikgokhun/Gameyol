package com.trendyol.celik.gokhun.ui.gamedetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trendyol.celik.gokhun.common.viewmodel.BaseViewModel
import com.trendyol.celik.gokhun.domain.gamedetail.GameDetailUseCase
import com.trendyol.celik.gokhun.domain.model.GameDetail
import com.trendyol.celik.gokhun.common.extensions.ResourceReactiveExtensions.subscribe
import com.trendyol.celik.gokhun.ui.gamedetail.GameDetailStatusViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val gameDetailUseCase: GameDetailUseCase
) : BaseViewModel() {

    private val stateLiveData: MutableLiveData<GameDetailStatusViewState> = MutableLiveData()

    fun getStateLiveData(): LiveData<GameDetailStatusViewState> = stateLiveData

    fun initializeViewModel(id: String) {
        fetchGameDetail(id)
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
            stateLiveData.value = GameDetailStatusViewState.Empty
        } else {
            stateLiveData.value = GameDetailStatusViewState.Success(gameDetail)
        }

    }

    private fun onGameDetailResponseFail(throwable: Throwable) {
        stateLiveData.value = GameDetailStatusViewState.Error(throwable)
    }

    private fun onGameDetailLoading() {
        stateLiveData.value = GameDetailStatusViewState.Loading
    }
}