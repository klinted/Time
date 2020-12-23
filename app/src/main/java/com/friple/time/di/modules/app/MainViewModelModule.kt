package com.friple.time.di.modules.app

import androidx.lifecycle.ViewModel
import com.friple.time.di.annotations.ViewModelKey
import com.friple.time.mvvm.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}