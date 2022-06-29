package com.trendyol.celik.gokhun.service

import com.trendyol.celik.gokhun.domain.model.response.detail.Game
import com.trendyol.celik.gokhun.domain.model.response.list.RawgData
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

    fun getData(): Single<RawgData> {
        return api.getRawgData()
    }

    fun getDetailsOfGame(id :String): Single<Game> {
        return api.getDetailsOfGame(id)
    }
}