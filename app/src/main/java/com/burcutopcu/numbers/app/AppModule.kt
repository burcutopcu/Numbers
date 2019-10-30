package com.burcutopcu.numbers.app

import android.content.Context
import com.burcutopcu.numbers.App
import com.burcutopcu.numbers.di.MainActivityModule
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(app: App): Context = app

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideApp(): App {
        return App()
    }

    @Singleton
    @Provides
    fun provideMainActivityModule(): MainActivityModule {
        return MainActivityModule()
    }

    @Singleton
    @Provides
    fun provideApiManager(): ApiManager {
        return ApiManager()
    }
}