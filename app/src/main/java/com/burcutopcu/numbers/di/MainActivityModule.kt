package com.burcutopcu.numbers.di

import androidx.lifecycle.ViewModelProviders
import com.burcutopcu.numbers.MainActivity
import com.burcutopcu.numbers.vm.MainActivityViewModel
import com.burcutopcu.numbers.vm.MainActivityViewModelFactory
import dagger.Module
import dagger.Provides
import androidx.lifecycle.ViewModelProvider
import com.burcutopcu.numbers.app.ApiManager
import com.burcutopcu.numbers.network.repo.NumbersRepo
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun bindMainActivity(): MainActivity
}

@Module
class MainActivityModule {

    @Provides
    @Singleton
    fun provideNumberViewModel(activity: MainActivity, factory: MainActivityViewModelFactory): MainActivityViewModel {
        return ViewModelProviders.of(activity, factory).get(MainActivityViewModel::class.java)
    }

    @Provides
    @Singleton
    fun getRepository(apiManager: ApiManager): NumbersRepo {
        return NumbersRepo(apiManager)
    }

    @Provides
    @Singleton
    fun getViewModelFactory(numbersRepo: NumbersRepo): ViewModelProvider.Factory {
        return MainActivityViewModelFactory(numbersRepo)
    }
}