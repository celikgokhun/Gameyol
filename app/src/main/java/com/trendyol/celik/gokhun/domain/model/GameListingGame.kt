package com.trendyol.celik.gokhun.domain.model

data class GameListingGame(
    val games: List<Game>,
    val pagination: String? = null
)