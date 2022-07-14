package com.trendyol.celik.gokhun.data.gamesearch.source.remote

import com.trendyol.celik.gokhun.data.gamesearch.source.GameSearchDataSource
import com.trendyol.celik.gokhun.data.gamesearch.source.remote.model.GameSearchResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GameSearchRemoteDataSource @Inject constructor(
    private val gameSearchService: GameSearchService
) : GameSearchDataSource.Remote {

    override fun fetchGameSearch(
        search: String?
    ): Observable<GameSearchResponse> {
        return gameSearchService
            .fetchSearchGame(search)
            .toObservable()
    }

}