package com.trendyol.celik.gokhun.di.listing

import com.trendyol.celik.gokhun.data.gamelisting.source.GameListingDataSource
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.GameListingRemoteDataSource
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.GameListingService
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

    /*
    companion object {
        @Provides
        @Singleton
        fun provideGameListingService(builder: RetrofitBuilder): GameListingService {
            return builder.build(SocialUrlConfig()).create(GameListingService::class.java)
        }
    }

     */

}