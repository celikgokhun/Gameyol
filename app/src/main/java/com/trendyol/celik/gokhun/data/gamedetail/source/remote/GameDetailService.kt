package com.trendyol.celik.gokhun.data.gamedetail.source.remote

import com.trendyol.celik.gokhun.data.gamedetail.source.remote.model.response.detail.GameDetailResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GameDetailService {
    @GET("/api/games/{id}?key=f6c5c10dcd914fc4aa2b69b347ff02e7")/// --------------
    fun fetchDetailsOfGame(
        @Path("id") id: String
    ): Single<GameDetailResponse>
}