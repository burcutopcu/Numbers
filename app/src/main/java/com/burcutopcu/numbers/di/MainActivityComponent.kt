package com.burcutopcu.numbers.di

import com.burcutopcu.numbers.App
import com.burcutopcu.numbers.MainActivity
import com.burcutopcu.numbers.app.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class, ActivityBuilder::class])
interface MainActivityComponent {

    @Component.Builder
    interface Builder
    {
        @BindsInstance
        fun application(activity: MainActivity): Builder

        fun build(): MainActivityComponent
    }

    fun inject (activity: MainActivity)
}