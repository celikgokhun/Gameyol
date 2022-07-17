package com.trendyol.celik.gokhun.data.gamedetail.source.remote

import com.trendyol.celik.gokhun.common.util.Constants
import com.trendyol.celik.gokhun.data.gamedetail.source.remote.model.GameDetailResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GameDetailService {
    @GET("/api/games/{id}?key=${Constants.API_KEY}")
    fun fetchDetailsOfGame(
        @Path("id") id: String
    ): Single<GameDetailResponse>
}