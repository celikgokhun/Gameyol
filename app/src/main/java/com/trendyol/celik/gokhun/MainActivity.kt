package com.trendyol.celik.gokhun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.trendyol.celik.gokhun.ui.detail.viewmodel.GameDetailsViewModel
import com.trendyol.celik.gokhun.ui.main.viewmodel.GameListingViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var gameListViewModel: GameListingViewModel
    private lateinit var gameDetailsViewModel: GameDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameListViewModel = ViewModelProviders.of(this).get(GameListingViewModel::class.java)
        gameDetailsViewModel = ViewModelProviders.of(this).get(GameDetailsViewModel::class.java)

            //gameListViewModel.refreshRawgAPIData()
            gameDetailsViewModel.refreshGameDetailAPIData("3498")

    }




}