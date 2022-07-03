package com.trendyol.celik.gokhun.service

import com.trendyol.celik.gokhun.data.gamedetail.source.remote.model.response.detail.ResponseGameDetail
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.GameListingService
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list.GameListingResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RawgRetrofit {

    private val BASE_URL = "https://api.rawg.io/api/"
    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(GameListingService.Games::class.java)

    /*
    fun fetchGameList(): Single<GameListingResponse> {
        return api
    }

     */

    /// all about Listing
    /*
    fun fetchDetailsOfGame(id :String): Single<ResponseGameDetail> {
        return api.fetchDetailsOfGame(id)
    }

     */
}