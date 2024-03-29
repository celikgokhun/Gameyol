package com.trendyol.celik.gokhun.ui.gamelisting

import com.trendyol.celik.gokhun.common.util.Constants.BASE_URL
import com.trendyol.celik.gokhun.data.gamelisting.source.GameListingDataSource
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.GameListingRemoteDataSource
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.GameListingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GameListingModule{

    @Singleton
    @Provides
    fun provideGameListingService() : GameListingService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(GameListingService::class.java)
    }

    @Singleton
    @Provides
    fun provideGameListingRemoteDataSourceBuilder() : GameListingDataSource.Remote{
        return GameListingRemoteDataSource(provideGameListingService())
    }
}