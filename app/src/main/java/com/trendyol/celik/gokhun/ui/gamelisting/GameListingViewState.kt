package com.trendyol.celik.gokhun.ui.gamelisting

import com.trendyol.celik.gokhun.domain.model.Game

sealed class GameListingStatusViewState {
    object Loading : GameListingStatusViewState()
    object Empty : GameListingStatusViewState()
    data class Success(val games: List<Game>?) : GameListingStatusViewState()
    data class Error(val throwable: Throwable) : GameListingStatusViewState()
}