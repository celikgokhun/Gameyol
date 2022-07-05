package com.trendyol.celik.gokhun.data.gamedetail.source

import com.trendyol.celik.gokhun.data.gamedetail.source.remote.model.response.detail.GameDetailResponse
import io.reactivex.rxjava3.core.Observable

interface GameDetailDataSource {
    interface Remote {
        fun fetchGameDetails(id: String):
                Observable<GameDetailResponse>
    }
}