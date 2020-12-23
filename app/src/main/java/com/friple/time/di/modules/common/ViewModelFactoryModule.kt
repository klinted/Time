package com.friple.time.di.modules.common

import androidx.lifecycle.ViewModelProvider
import com.friple.time.mvvm.common.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory

    companion object {
        @Provides
        fun bindFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory = factory
    }
}