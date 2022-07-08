package com.trendyol.celik.gokhun.data.gamelisting.source.remote

import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list.GameListingResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GameListingService {
    // to get list
    @GET("games?key=f6c5c10dcd914fc4aa2b69b347ff02e7")
    fun fetchGamesList(
    ): Single<GameListingResponse>

    @GET("games?key=f6c5c10dcd914fc4aa2b69b347ff02e7")
    fun fetchNextGamesList(
        @Query("page") page: Int?= null,
    ): Single<GameListingResponse>


}