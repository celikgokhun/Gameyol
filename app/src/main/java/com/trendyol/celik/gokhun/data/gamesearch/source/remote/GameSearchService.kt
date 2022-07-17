package com.trendyol.celik.gokhun.data.gamesearch.source.remote

import com.trendyol.celik.gokhun.common.util.Constants
import com.trendyol.celik.gokhun.data.gamesearch.source.remote.model.GameSearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GameSearchService {

    @GET("games?key=${Constants.API_KEY}")
    fun fetchSearchGame(
        @Query("search") search: String?= null,
    ): Single<GameSearchResponse>
}