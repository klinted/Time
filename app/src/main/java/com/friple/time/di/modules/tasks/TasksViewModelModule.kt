package com.friple.time.di.modules.tasks

import androidx.lifecycle.ViewModel
import com.friple.time.di.annotations.ViewModelKey
import com.friple.time.mvvm.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TasksViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindTimeViewModel(homeViewModel: HomeViewModel): ViewModel
}