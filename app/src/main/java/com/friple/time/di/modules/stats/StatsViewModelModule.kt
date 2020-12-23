package com.friple.time.di.modules.stats

import androidx.lifecycle.ViewModel
import com.friple.time.di.annotations.ViewModelKey
import com.friple.time.mvvm.viewmodel.StatsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class StatsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(StatsViewModel::class)
    abstract fun bindStatsViewModel(statsViewModel: StatsViewModel): ViewModel
}