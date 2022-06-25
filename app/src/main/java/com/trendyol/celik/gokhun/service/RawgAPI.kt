package com.trendyol.celik.gokhun.service

import com.trendyol.celik.gokhun.domain.model.response.detail.Game
import com.trendyol.celik.gokhun.domain.model.response.list.RawgData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RawgAPI {

    // to get list
    @GET("games?key=f6c5c10dcd914fc4aa2b69b347ff02e7")
    fun getRawgData () : Single<RawgData>


    // to get detail of determined game
    @GET("/api/games/{id}?key=f6c5c10dcd914fc4aa2b69b347ff02e7")/// --------------
    fun getDetailsOfGame(
        @Path("id") id: String
    ): Single<Game>



}