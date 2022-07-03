package com.trendyol.celik.gokhun.di.listing.ui

import androidx.lifecycle.ViewModelProviders
import com.trendyol.celik.gokhun.common.di.FragmentScope
import com.trendyol.celik.gokhun.common.di.ViewModelInjection
import com.trendyol.celik.gokhun.ui.gamelisting.GameListingAdapter
import com.trendyol.celik.gokhun.ui.gamelisting.GameListingFragment
import com.trendyol.celik.gokhun.ui.gamelisting.viewmodel.GameListingViewModel
import dagger.Module
import dagger.Provides

@Module
class GameListingFragmentModule {

    @FragmentScope
    @ViewModelInjection
    @Provides
    fun provideGameListingViewModel(gameListingFragment: GameListingFragment): GameListingViewModel {
        // return gameListingFragment.fragmentViewModelProvider.get(GameListingViewModel::class.java)
        return ViewModelProviders.of(gameListingFragment)[GameListingViewModel::class.java]
    }

    @FragmentScope
    @Provides
    fun provideGameListingAdapter(): GameListingAdapter {
        return GameListingAdapter()
    }
}