package com.trendyol.celik.gokhun.data.gamelisting.repository

import com.trendyol.celik.gokhun.common.extensions.Resource
import com.trendyol.celik.gokhun.common.extensions.ResourceReactiveExtensions.remote
import com.trendyol.celik.gokhun.data.gamelisting.source.GameListingDataSource
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.GameListingResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GameListingRepository @Inject constructor(
    private val gameListingDataSource: GameListingDataSource.Remote
) {
    fun fetchGames(
    ) : Observable<Resource<GameListingResponse>> {
        return gameListingDataSource
        .fetchGames()
        .remote()
    }

    fun fetchNextGames(
        next : String?
    ) : Observable<Resource<GameListingResponse>> {
        return gameListingDataSource
            .fetchNextGames(next)
            .remote()
    }

}