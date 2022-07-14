package com.trendyol.celik.gokhun.data.gamesearch.repository

import com.trendyol.celik.gokhun.base.extensions.Resource
import com.trendyol.celik.gokhun.base.extensions.ResourceReactiveExtensions.remote
import com.trendyol.celik.gokhun.data.gamesearch.source.GameSearchDataSource
import com.trendyol.celik.gokhun.data.gamesearch.source.remote.model.GameSearchResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GameSearchRepository @Inject constructor(
    private val gameSearchDataSource: GameSearchDataSource.Remote
) {

    fun fetchSearchGame(
        search : String?
    ) : Observable<Resource<GameSearchResponse>> {
        return gameSearchDataSource
            .fetchGameSearch(search)
            .remote()
    }


}