package com.friple.time.di.modules.common

import com.friple.time.di.modules.app.AppModule
import com.friple.time.di.modules.app.MainViewModelModule
import com.friple.time.di.modules.common.FragmentBuildersModule
import com.friple.time.mvvm.view.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector (
        modules = [
            FragmentBuildersModule::class,
            AppModule::class,
            MainViewModelModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}
