package com.trendyol.celik.gokhun.data.gamedetail.repository

import com.trendyol.celik.gokhun.common.extensions.Resource
import com.trendyol.celik.gokhun.common.extensions.ResourceReactiveExtensions.remote
import com.trendyol.celik.gokhun.data.gamedetail.source.GameDetailDataSource
import com.trendyol.celik.gokhun.data.gamedetail.source.remote.model.GameDetailResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GameDetailRepository @Inject constructor(
    private val gameDetailDataSource: GameDetailDataSource.Remote
) {
    fun fetchGameDetail(id: String): Observable<Resource<GameDetailResponse>> {
        return gameDetailDataSource
            .fetchGameDetails(id)
            .remote()
    }
}