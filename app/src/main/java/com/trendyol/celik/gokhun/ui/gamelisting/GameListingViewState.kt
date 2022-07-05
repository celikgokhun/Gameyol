package com.trendyol.celik.gokhun.ui.gamelisting

import com.trendyol.celik.gokhun.domain.model.Game

sealed class GameListingStatusViewState {
    object IsLoading : GameListingStatusViewState()
    object IsDoneLoading : GameListingStatusViewState()
    data class ShowData(val games: List<Game>) : GameListingStatusViewState()
    data class Error(val error: String) : GameListingStatusViewState()
}