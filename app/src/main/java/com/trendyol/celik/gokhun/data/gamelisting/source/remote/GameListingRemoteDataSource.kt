package com.trendyol.celik.gokhun.data.gamelisting.source.remote

import com.trendyol.celik.gokhun.data.gamelisting.source.GameListingDataSource
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list.GameListingResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GameListingRemoteDataSource @Inject constructor(
    private val gameListingService: GameListingService.Games,
) : GameListingDataSource.Remote {

    override fun fetchGames(
    ): Observable<GameListingResponse> {
        return gameListingService
            .fetchGamesList()
            .toObservable()
    }
}