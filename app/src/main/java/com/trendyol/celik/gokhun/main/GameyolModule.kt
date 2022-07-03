package com.trendyol.celik.gokhun.main

import com.trendyol.celik.gokhun.ui.GameListingModule
import dagger.Module

@Module(
    includes = [
        GameListingModule::class
            ]
)
interface GameyolModule