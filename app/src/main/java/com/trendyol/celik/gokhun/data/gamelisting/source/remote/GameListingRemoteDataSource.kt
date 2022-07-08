package com.trendyol.celik.gokhun.data.gamelisting.source.remote

import com.trendyol.celik.gokhun.data.gamelisting.source.GameListingDataSource
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list.GameListingResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GameListingRemoteDataSource @Inject constructor(
    private val gameListingService: GameListingService,
) : GameListingDataSource.Remote {

    override fun fetchGames(
    ): Observable<GameListingResponse> {
        return gameListingService
            .fetchGamesList()
            .toObservable()
    }

    override fun fetchNextGames(
        next: String?
    ): Observable<GameListingResponse> {
        return gameListingService
            .fetchNextGamesList(next!!.split("&page=")[1].toInt())
            .toObservable()
    }
}