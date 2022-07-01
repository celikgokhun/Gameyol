package com.trendyol.celik.gokhun.ui

import com.trendyol.celik.gokhun.di.listing.GameListingDataModule
import com.trendyol.celik.gokhun.di.listing.GameListingFragmentBuilderModule
import com.trendyol.celik.gokhun.di.listing.GameListingViewModelModule
import dagger.Module

@Module(
    includes = [
        GameListingViewModelModule::class,
        GameListingFragmentBuilderModule::class,
        GameListingDataModule::class
    ]
)
class GameListingModule
