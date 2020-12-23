package com.friple.time.di.modules.common

import com.friple.time.di.modules.home.HomeModule
import com.friple.time.di.modules.home.HomeViewModelModule
import com.friple.time.di.modules.stats.StatsModule
import com.friple.time.di.modules.stats.StatsViewModelModule
import com.friple.time.di.modules.tasks.TasksModule
import com.friple.time.di.modules.tasks.TasksViewModelModule
import com.friple.time.mvvm.view.containers.TabContainerFragment
import com.friple.time.mvvm.view.fragments.ProfileScreen
import com.friple.time.mvvm.view.fragments.StatsScreen
import com.friple.time.mvvm.view.fragments.HomeScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeTabContainerFragment(): TabContainerFragment

    @ContributesAndroidInjector( modules = [
        TasksModule::class,
        TasksViewModelModule::class
    ])
    abstract fun contributeTasksScreen(): HomeScreen

    @ContributesAndroidInjector( modules = [
        HomeModule::class,
        HomeViewModelModule::class
    ])
    abstract fun contributeHomeScreen(): ProfileScreen

    @ContributesAndroidInjector( modules = [
        StatsModule::class,
        StatsViewModelModule::class
    ])
    abstract fun contributeStatsScreen(): StatsScreen
}