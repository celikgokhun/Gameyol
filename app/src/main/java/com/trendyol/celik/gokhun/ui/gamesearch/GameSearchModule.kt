package com.trendyol.celik.gokhun.ui.gamesearch

import com.trendyol.celik.gokhun.common.util.Constants.BASE_URL
import com.trendyol.celik.gokhun.data.gamesearch.source.GameSearchDataSource
import com.trendyol.celik.gokhun.data.gamesearch.source.remote.GameSearchRemoteDataSource
import com.trendyol.celik.gokhun.data.gamesearch.source.remote.GameSearchService
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
class GameSearchModule {


    @Singleton
    @Provides
    fun provideGameSearchService() : GameSearchService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(GameSearchService::class.java)
    }

    @Singleton
    @Provides
    fun provideGameSearchRemoteDataSourceBuilder() : GameSearchDataSource.Remote{
        return GameSearchRemoteDataSource(provideGameSearchService())
    }
}