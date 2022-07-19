package com.trendyol.celik.gokhun.domain.gamesearch

import com.trendyol.celik.gokhun.common.extensions.Resource
import com.trendyol.celik.gokhun.common.extensions.mapOnData
import com.trendyol.celik.gokhun.data.gamesearch.repository.GameSearchRepository
import com.trendyol.celik.gokhun.domain.model.GameListingGame
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GameSearchUseCase  @Inject constructor(
    private val gameSearchRepository: GameSearchRepository,
    private val gameSearchMapper: GameSearchMapper
) {

    fun fetchGameSearch(search: String?): Observable<Resource<GameListingGame>> {
        return gameSearchRepository
            .fetchSearchGame(search)
            .mapOnData {
                gameSearchMapper.mapFromResponse(it)
            }
    }

    fun fetchGameSearchNext(next: String?): Observable<Resource<GameListingGame>> {
        return gameSearchRepository
            .fetchSearchGameNext(next)
            .mapOnData {
                gameSearchMapper.mapFromResponse(it)
            }
    }
}
