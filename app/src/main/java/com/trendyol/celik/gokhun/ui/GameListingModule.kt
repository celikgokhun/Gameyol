package com.trendyol.celik.gokhun.ui

import com.trendyol.celik.gokhun.data.gamelisting.source.GameListingDataSource
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.GameListingRemoteDataSource
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.GameListingService
import com.trendyol.celik.gokhun.ui.gamelisting.GameListingAdapter
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

    private val BASE_URL = "https://api.rawg.io/api/"

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
    fun provideRemoteDataSourceBuilder() : GameListingDataSource.Remote{
        return GameListingRemoteDataSource(provideGameListingService())
    }
    // fun fetchGames() : GameListingResponse = provideGameListingService().fetchGamesList()




}