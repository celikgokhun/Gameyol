package com.trendyol.celik.gokhun.service

import com.trendyol.celik.gokhun.data.gamedetail.source.remote.model.response.detail.Game
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list.GameListingResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RawgAPIService {

    private val BASE_URL = "https://api.rawg.io/api/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(RawgAPI::class.java)

    fun fetchGameList(): Single<GameListingResponse> {
        return api.fetchGameListing()
    }

    fun getDetailsOfGame(id :String): Single<Game> {
        return api.fetchDetailsOfGame(id)
    }
}