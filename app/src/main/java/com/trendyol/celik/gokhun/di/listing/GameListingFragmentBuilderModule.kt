package com.trendyol.celik.gokhun.di.listing

import com.trendyol.celik.gokhun.common.di.FragmentScope
import com.trendyol.celik.gokhun.ui.gamelisting.GameListingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class GameListingFragmentBuilderModule {

    @ContributesAndroidInjector(modules = [GameListingFragmentModule::class])
    @FragmentScope
    abstract fun provideGameListingFragment(): GameListingFragment
}