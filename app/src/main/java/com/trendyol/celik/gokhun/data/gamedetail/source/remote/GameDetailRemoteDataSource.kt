package com.trendyol.celik.gokhun.data.gamedetail.source.remote

import com.trendyol.celik.gokhun.data.gamedetail.source.GameDetailDataSource
import com.trendyol.celik.gokhun.data.gamedetail.source.remote.model.GameDetailResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GameDetailRemoteDataSource @Inject constructor(
    private val gameDetailService: GameDetailService
    ) : GameDetailDataSource.Remote{

    override fun fetchGameDetails(id: String
    ): Observable<GameDetailResponse> {
        return gameDetailService
            .fetchDetailsOfGame(id)
            .toObservable()
    }

}