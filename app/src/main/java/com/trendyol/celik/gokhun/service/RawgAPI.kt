package com.trendyol.celik.gokhun.service

import com.trendyol.celik.gokhun.data.gamedetail.source.remote.model.response.detail.Game
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list.GameListingResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RawgAPI {

    // to get list
    @GET("games?key=f6c5c10dcd914fc4aa2b69b347ff02e7")
    fun fetchGameListing () : Single<GameListingResponse>


    // to get detail of determined game
    @GET("/api/games/{id}?key=f6c5c10dcd914fc4aa2b69b347ff02e7")/// --------------
    fun fetchDetailsOfGame(
        @Path("id") id: String
    ): Single<Game>



}