package com.trendyol.celik.gokhun.ui.platformlisting

import com.trendyol.celik.gokhun.data.platformlisting.source.PlatformListingDataSource
import com.trendyol.celik.gokhun.data.platformlisting.source.remote.PlatformListingRemoteDataSource
import com.trendyol.celik.gokhun.data.platformlisting.source.remote.PlatformListingService
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
class PlatformListingModule{

    private val BASE_URL = "https://api.rawg.io/api/"

    @Singleton
    @Provides
    fun providePlatformListingService() : PlatformListingService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(PlatformListingService::class.java)
    }

    @Singleton
    @Provides
    fun providePlatformListingRemoteDataSourceBuilder() : PlatformListingDataSource.Remote{
        return PlatformListingRemoteDataSource(providePlatformListingService())
    }
}