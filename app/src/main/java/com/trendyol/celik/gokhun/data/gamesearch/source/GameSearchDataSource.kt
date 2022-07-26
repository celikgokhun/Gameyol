package com.trendyol.celik.gokhun.data.gamesearch.source

import com.trendyol.celik.gokhun.data.gamesearch.source.remote.model.GameSearchResponse
import io.reactivex.rxjava3.core.Observable

interface GameSearchDataSource {
    interface Remote {
        fun fetchGameSearch(
            search: String?
        ): Observable<GameSearchResponse>

        fun fetchGameSearchByPlatform(
            search: String?,
            parentPlatform: String?
        ): Observable<GameSearchResponse>

        fun fetchGameSearchNext(
            next: String?
        ): Observable<GameSearchResponse>


    }
}