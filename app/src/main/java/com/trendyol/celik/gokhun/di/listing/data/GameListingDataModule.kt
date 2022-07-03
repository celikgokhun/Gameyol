package com.trendyol.celik.gokhun.di.listing.data

import com.trendyol.celik.gokhun.data.gamelisting.source.GameListingDataSource
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.GameListingRemoteDataSource
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.GameListingService
import com.trendyol.celik.gokhun.service.RawgRetrofit
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class GameListingDataModule {
    @Binds
    internal abstract fun provideGameListingRemoteDataSource(
        gameListingRemoteDataSource: GameListingRemoteDataSource,
    ): GameListingDataSource.Remote


    companion object {
        @Provides
        @Singleton
        fun provideGameListingService(builder: RawgRetrofit): GameListingService.Games {
            return builder.api
        }
    }
}