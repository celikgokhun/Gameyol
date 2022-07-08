package com.trendyol.celik.gokhun.data.gamelisting.repository

import com.trendyol.celik.gokhun.data.gamelisting.source.GameListingDataSource
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list.GameListingResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GameListingRepository @Inject constructor(
    private val gameListingDataSource: GameListingDataSource.Remote
) {
    fun fetchGames(
    ) : Observable<GameListingResponse> {
        return gameListingDataSource
        .fetchGames()
            .map{it}
    }

    fun fetchNextGames(
        next : String?
    ) : Observable<GameListingResponse> {
        return gameListingDataSource
            .fetchNextGames(next)
            .map{it}
    }

}