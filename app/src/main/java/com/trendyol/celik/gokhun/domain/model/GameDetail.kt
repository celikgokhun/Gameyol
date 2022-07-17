package com.trendyol.celik.gokhun.domain.model

import com.trendyol.celik.gokhun.data.gamedetail.source.remote.model.Publisher
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.Genre

data class GameDetail (
    val id: Int?,
    val backGroundImage: String?,
    val nameOriginal: String?,
    val metaCritic: Int?,
    val description: String?,
    val released: String?,
    val genres: List<Genre?>?,
    val playtime: Int?,
    val publishers: List<Publisher?>?,
    val redditUrl: String?,
    val website: String?
    )
