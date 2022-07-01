package com.trendyol.celik.gokhun.domain.gamelisting

import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list.GameListingResponse
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list.GameListingGameResponse
import com.trendyol.celik.gokhun.domain.model.GameListingGame
import com.trendyol.celik.gokhun.domain.model.Game
import javax.inject.Inject

class GameListingMapper @Inject constructor() {

    fun mapFromResponse(
        response: GameListingResponse,
    ): GameListingGame {
        return GameListingGame(
            games = mapGames(response.results)
        )
    }

    private fun mapGames(games: List<GameListingGameResponse?>?): List<Game> {
        return games?.mapNotNull {
            mapFromGameDetailResponseToGameDetail(it)
        }.orEmpty()
    }

    private fun mapFromGameDetailResponseToGameDetail(response: GameListingGameResponse?): Game {
        return Game(
            id = response?.id.toString(),
            name = response?.name.orEmpty(),
            backgroundImage = response?.backgroundImage.orEmpty()
        )
    }
}