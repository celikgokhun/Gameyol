package com.trendyol.celik.gokhun.domain.model.ui

import com.trendyol.celik.gokhun.domain.model.response.detail.Publisher
import com.trendyol.celik.gokhun.domain.model.response.list.Genre

data class GameOnDetails (
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
