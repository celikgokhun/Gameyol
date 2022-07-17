package com.trendyol.celik.gokhun.data.gamelisting.source.remote

import com.trendyol.celik.gokhun.common.util.Constants.API_KEY
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list.GameListingResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GameListingService {
    // to get list
    @GET("games?key=$API_KEY")
    fun fetchGamesList(
    ): Single<GameListingResponse>

    @GET("games?key=$API_KEY")
    fun fetchNextGamesList(
        @Query("page") page: Int?= null,
    ): Single<GameListingResponse>


}