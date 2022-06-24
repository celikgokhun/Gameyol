package com.trendyol.celik.gokhun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.trendyol.celik.gokhun.viewmodel.GameListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var gameListViewModel: GameListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameListViewModel = ViewModelProviders.of(this).get(GameListViewModel::class.java)

        text.setOnClickListener {
            gameListViewModel.refreshRawgAPIData()
        }
    }




}