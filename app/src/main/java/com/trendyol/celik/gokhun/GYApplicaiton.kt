package com.trendyol.celik.gokhun

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GYApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

}