package com.trendyol.celik.gokhun.data.gamelisting.source

import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list.GameListingResponse
import io.reactivex.rxjava3.core.Observable

interface GameListingDataSource {
    interface Remote {
        fun fetchGames(
        ): Observable<GameListingResponse>

        fun fetchNextGames(
            next: String?
        ): Observable<GameListingResponse>
    }
}