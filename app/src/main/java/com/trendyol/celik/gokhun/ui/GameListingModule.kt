package com.trendyol.celik.gokhun.ui

import com.trendyol.celik.gokhun.di.listing.data.GameListingDataModule
import com.trendyol.celik.gokhun.di.listing.ui.GameListingFragmentBuilderModule
import com.trendyol.celik.gokhun.di.listing.ui.GameListingViewModelModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        GameListingViewModelModule::class,
        GameListingFragmentBuilderModule::class,
        GameListingDataModule::class
    ]
)
class GameListingModule
