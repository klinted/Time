package com.friple.time.di.modules.home

import androidx.lifecycle.ViewModel
import com.friple.time.di.annotations.ViewModelKey
import com.friple.time.mvvm.viewmodel.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindHomeViewModel(timeViewModel: ProfileViewModel): ViewModel
}