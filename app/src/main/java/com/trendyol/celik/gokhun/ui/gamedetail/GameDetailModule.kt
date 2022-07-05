package com.trendyol.celik.gokhun.ui.gamedetail

import com.trendyol.celik.gokhun.data.gamedetail.source.GameDetailDataSource
import com.trendyol.celik.gokhun.data.gamedetail.source.remote.GameDetailRemoteDataSource
import com.trendyol.celik.gokhun.data.gamedetail.source.remote.GameDetailService
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
class GameDetailModule {

    private val BASE_URL = "https://api.rawg.io/api/"

    @Singleton
    @Provides
    fun provideGameDetailService() : GameDetailService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(GameDetailService::class.java)
    }

    @Singleton
    @Provides
    fun provideGameDetailRemoteDataSourceBuilder() : GameDetailDataSource.Remote{
        return GameDetailRemoteDataSource(provideGameDetailService())
    }
}