package com.trendyol.celik.gokhun.data.gamedetail.repository

import com.trendyol.celik.gokhun.data.gamedetail.source.GameDetailDataSource
import com.trendyol.celik.gokhun.data.gamedetail.source.remote.model.response.detail.GameDetailResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GameDetailRepository @Inject constructor(
    private val gameDetailDataSource: GameDetailDataSource.Remote
) {
    fun fetchGameDetail(id: String): Observable<GameDetailResponse> {
        return gameDetailDataSource
            .fetchGameDetails(id)
    }
}