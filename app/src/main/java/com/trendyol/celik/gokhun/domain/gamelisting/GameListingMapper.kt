package com.trendyol.celik.gokhun.domain.gamelisting

import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list.GameListingResponse
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list.GameResultResponse
import com.trendyol.celik.gokhun.domain.model.GameListingGame
import com.trendyol.celik.gokhun.domain.model.GameResult
import javax.inject.Inject

class GameListingMapper @Inject constructor() {

    fun mapFromResponse(
        response: GameListingResponse,
    ): GameListingGame {
        return GameListingGame(
            games = mapGames(response.results)
        )
    }

    private fun mapGames(games: List<GameResultResponse?>?): List<GameResult> {
        return games?.mapNotNull {
            mapFromGameDetailResponseToGameDetail(it)
        }.orEmpty()
    }

    private fun mapFromGameDetailResponseToGameDetail(response: GameResultResponse?): GameResult? {
        if (response?.id == null) return null

        return GameResult(
            id = response.id,
            name = response.name.orEmpty(),
            backgroundImage = response.backgroundImage.orEmpty()
        )
    }
}