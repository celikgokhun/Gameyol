package com.trendyol.celik.gokhun.domain.gamesearch

import com.trendyol.celik.gokhun.common.util.elementExtractorPlatformForSearch
import com.trendyol.celik.gokhun.data.gamesearch.source.remote.model.GameSearchGameResponse
import com.trendyol.celik.gokhun.data.gamesearch.source.remote.model.GameSearchResponse
import com.trendyol.celik.gokhun.domain.model.Game
import com.trendyol.celik.gokhun.domain.model.GameListingGame
import javax.inject.Inject

class GameSearchMapper @Inject constructor() {

    fun mapFromResponse(response: GameSearchResponse?): GameListingGame {
        return GameListingGame(
            games = mapGames(response?.results),
            pagination = response?.next
        )
    }

    private fun mapGames(games: List<GameSearchGameResponse?>?): List<Game> {
        return games?.mapNotNull {
            mapFromGameDetailResponseToGameDetail(it)
        }?.filter {
            it.id != null
        }.orEmpty()
    }

    private fun mapFromGameDetailResponseToGameDetail(response: GameSearchGameResponse?): Game {
        return Game(
            id = response?.id.toString(),
            name = response?.name.orEmpty(),
            backgroundImage = response?.backgroundImage.orEmpty(),
            platforms = elementExtractorPlatformForSearch(response?.platforms.orEmpty())
        )
    }
}