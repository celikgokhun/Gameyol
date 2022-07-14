package com.trendyol.celik.gokhun.data.gamesearch.source.remote

import com.trendyol.celik.gokhun.data.gamesearch.source.remote.model.GameSearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GameSearchService {

    @GET("games?key=f6c5c10dcd914fc4aa2b69b347ff02e7")
    fun fetchSearchGame(
        @Query("search") search: String?= null,
    ): Single<GameSearchResponse>
}