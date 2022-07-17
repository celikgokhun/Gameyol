package com.trendyol.celik.gokhun.domain.gamedetail

import com.trendyol.celik.gokhun.data.gamedetail.source.remote.model.GameDetailResponse
import com.trendyol.celik.gokhun.domain.model.GameDetail
import javax.inject.Inject

class GameDetailMapper  @Inject constructor() {
    fun mapFromResponse(
        response: GameDetailResponse
    ): GameDetail {
        return GameDetail(
            id = response.id,
            backGroundImage = response.backgroundImage,
            nameOriginal = response.nameOriginal,
            metaCritic = response.metaCritic,
            description = response.description,
            released = response.released,
            genres = response.genres,
            playtime = response.playtime,
            publishers = response.publishers,
            redditUrl = response.redditUrl,
            website = response.website
        )
    }
}