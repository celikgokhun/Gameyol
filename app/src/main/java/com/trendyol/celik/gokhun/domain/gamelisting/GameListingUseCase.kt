package com.trendyol.celik.gokhun.domain.gamelisting

import com.trendyol.celik.gokhun.data.gamelisting.repository.GameListingRepository
import com.trendyol.celik.gokhun.domain.model.Game
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GameListingUseCase @Inject constructor(
    private val gameListingRepository: GameListingRepository,
    private val gameListingMapper: GameListingMapper
) {
    fun fetchGames(): Observable<List<Game>> {
        return gameListingRepository
            .fetchGames()
            .map { gameListingMapper.mapFromResponse(it) }
    }
}
