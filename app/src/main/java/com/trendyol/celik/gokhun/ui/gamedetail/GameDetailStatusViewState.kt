package com.trendyol.celik.gokhun.ui.gamedetail

import com.trendyol.celik.gokhun.domain.model.GameDetail

sealed class GameDetailStatusViewState {
    object Loading : GameDetailStatusViewState()
    object Empty : GameDetailStatusViewState()
    data class Success(val game: GameDetail?) : GameDetailStatusViewState()
    data class Error(val throwable: Throwable) : GameDetailStatusViewState()
}