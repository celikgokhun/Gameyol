package com.trendyol.celik.gokhun.app

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.trendyol.celik.gokhun.main.GameyolModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        GameyolModule::class
    ]
)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindApplication(application: Application): Application

    companion object {

        @Provides
        @Singleton
        fun provideContext(application: Application): Context {
            return application.applicationContext
        }

        @Provides
        @Singleton
        fun provideApplicationResources(context: Context): Resources {
            return context.resources
        }

    }

}