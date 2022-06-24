package com.trendyol.celik.gokhun.service

import com.trendyol.celik.gokhun.model.list.RawgData
import io.reactivex.Single
import retrofit2.http.GET

interface RawgAPI {

    @GET("games?key=f6c5c10dcd914fc4aa2b69b347ff02e7")
    fun getRawgData () : Single<RawgData>
}