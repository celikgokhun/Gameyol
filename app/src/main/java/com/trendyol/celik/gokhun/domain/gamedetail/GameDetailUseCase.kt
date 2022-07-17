package com.trendyol.celik.gokhun.domain.gamedetail

import com.trendyol.celik.gokhun.common.extensions.Resource
import com.trendyol.celik.gokhun.common.extensions.mapOnData
import com.trendyol.celik.gokhun.data.gamedetail.repository.GameDetailRepository
import com.trendyol.celik.gokhun.domain.model.GameDetail
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GameDetailUseCase @Inject constructor(
    private val gameDetailRepository: GameDetailRepository,
    private val gameDetailMapper: GameDetailMapper
) {
    fun fetchGameDetail(id :String): Observable<Resource<GameDetail>> {
        return gameDetailRepository
            .fetchGameDetail(id)
            .mapOnData {
                gameDetailMapper.mapFromResponse(it)
            }
    }
}