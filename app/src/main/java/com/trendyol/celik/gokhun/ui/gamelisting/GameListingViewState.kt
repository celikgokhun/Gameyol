package com.trendyol.celik.gokhun.ui.gamelisting

import com.trendyol.celik.gokhun.domain.model.GameListingGame
sealed class GameListingPageViewState {
    object IsLoading : GameListingPageViewState()
    object IsDoneLoading : GameListingPageViewState()
    data class ShowData(val games: GameListingGame) : GameListingPageViewState()
    data class Error(val error: String) : GameListingPageViewState()
}