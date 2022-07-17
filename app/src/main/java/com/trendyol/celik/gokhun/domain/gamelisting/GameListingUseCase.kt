package com.trendyol.celik.gokhun.domain.gamelisting

import com.trendyol.celik.gokhun.common.extensions.Resource
import com.trendyol.celik.gokhun.common.extensions.mapOnData
import com.trendyol.celik.gokhun.data.gamelisting.repository.GameListingRepository
import com.trendyol.celik.gokhun.domain.model.GameListingGame
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GameListingUseCase @Inject constructor(
    private val gameListingRepository: GameListingRepository,
    private val gameListingMapper: GameListingMapper
) {
    fun fetchGames(): Observable<Resource<GameListingGame>> {
        return gameListingRepository
            .fetchGames()
            .mapOnData {
                gameListingMapper.mapFromResponse(it)
            }
    }

    fun fetchNextGames(next: String?): Observable<Resource<GameListingGame>> {
        return gameListingRepository
            .fetchNextGames(next)
            .mapOnData {
                gameListingMapper.mapFromResponse(it)
            }
    }


}
