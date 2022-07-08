package com.trendyol.celik.gokhun.ui.gamelisting

import com.trendyol.celik.gokhun.domain.model.GameListingGame

data class GameListingPageViewState(
    val gameListing: GameListingGame
) {
    fun addNewPage(newPage: GameListingGame): GameListingPageViewState {
        val newList = gameListing.games + newPage.games
        return copy(gameListing.copy(games = newList, pagination = newPage.pagination))
    }

    val getPageQueries = gameListing.pagination
}