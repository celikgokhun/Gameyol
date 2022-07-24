package com.trendyol.celik.gokhun.domain.gamelisting

import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.GameListingResponse
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.GameListingGameResponse
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.Platform
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.PlatformDetail
import com.trendyol.celik.gokhun.domain.model.Game
import com.trendyol.celik.gokhun.domain.model.GameListingGame
import javax.inject.Inject

class GameListingMapper @Inject constructor() {

    fun mapFromResponse(response: GameListingResponse?): GameListingGame {
        return GameListingGame(
            games = mapGames(response?.results),
            pagination = response?.next
        )
    }

    private fun mapGames(games: List<GameListingGameResponse?>?): List<Game> {
        return games?.mapNotNull {
            mapFromGameDetailResponseToGameDetail(it)
        }?.filter {
            it.id != null
        }.orEmpty()
    }


    private fun mapFromGameDetailResponseToGameDetail(response: GameListingGameResponse?): Game {
        return Game(
            id = response?.id.toString(),
            name = response?.name.orEmpty(),
            backgroundImage = response?.backgroundImage.orEmpty(),
            platforms = mapPlatforms(response?.platforms.orEmpty())
        )
    }

    private fun mapPlatforms(platformList: List<Platform?>?): String{
        var platformDetailsList: List<PlatformDetail?>?
        var allPlatforms =""
        for (item in platformList!!){
            platformDetailsList = listOf(item?.platform)
            for (item in platformDetailsList){
                allPlatforms = "$allPlatforms " + item?.
                name?.replace(" ","") + ", "
            }
        }
        return allPlatforms.dropLast(2).lowercase()
    }
}