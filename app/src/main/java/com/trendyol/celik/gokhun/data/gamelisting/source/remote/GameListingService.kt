package com.trendyol.celik.gokhun.data.gamelisting.source.remote

import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list.GameListingResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface GameListingService {
    // to get list
    interface Games {
        @GET("games?key=f6c5c10dcd914fc4aa2b69b347ff02e7")
        fun fetchGamesList(
        ): Single<GameListingResponse>
    }
}