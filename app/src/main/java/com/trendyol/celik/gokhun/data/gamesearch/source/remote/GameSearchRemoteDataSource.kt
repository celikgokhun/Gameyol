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


    override fun fetchGameSearchByPlatform(
        search: String?,
        parentPlatform: String?
    ): Observable<GameSearchResponse> {
        return gameSearchService
            .fetchSearchGameByPlatform(search, parentPlatform)
            .toObservable()
    }

    override fun fetchGameSearchNext(
        next: String?
    ): Observable<GameSearchResponse> {
        return gameSearchService
            .fetchSearchNextGame(
                next!!
                    .split("&page=")[1]
                    .split("&")[0]
                    .toInt(),

                next
                    .split("&search=")[1])

            .toObservable()
    }

}