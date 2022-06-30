package com.trendyol.celik.gokhun.domain.model

import com.trendyol.celik.gokhun.data.gamedetail.source.remote.model.response.detail.Publisher
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list.Genre

data class GameOnDetail (
    val id: Int?,
    val nameOriginal: String?,
    val metaCritic: Int?,
    val description: String?,
    val released: String?,
    val genres: List<Genre>?,
    val playtime: Int?,
    val publishers: List<Publisher>?,
    val redditUrl: String?,
    val website: String?
    )
