package com.trendyol.celik.gokhun.di.listing

import androidx.lifecycle.ViewModel
import com.trendyol.celik.gokhun.common.di.ViewModelKey
import com.trendyol.celik.gokhun.ui.gamelisting.viewmodel.GameListingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GameListingViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GameListingViewModel::class)
    abstract fun provideGameListingViewModel(viewModel: GameListingViewModel): ViewModel
}