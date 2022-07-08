package com.trendyol.celik.gokhun.ui.gamelisting

import com.trendyol.celik.gokhun.domain.model.GameListingGame

sealed class GameListingStatusViewState {
    object Loading : GameListingStatusViewState()
    object Empty : GameListingStatusViewState()
    data class Success(val games: GameListingGame?) : GameListingStatusViewState()
    data class Error(val throwable: Throwable) : GameListingStatusViewState()
}