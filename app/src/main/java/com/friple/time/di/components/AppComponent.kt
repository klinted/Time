package com.friple.time.di.components

import android.app.Application
import com.friple.time.appconfig.BaseApplication
import com.friple.time.di.modules.app.*
import com.friple.time.di.modules.common.ActivityBuildersModule
import com.friple.time.di.modules.common.FragmentBuildersModule
import com.friple.time.di.modules.common.ViewModelFactoryModule
import com.friple.time.di.modules.navigation.LocalNavigationModule
import com.friple.time.di.modules.navigation.NavigationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        FragmentBuildersModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
        NavigationModule::class,
        LocalNavigationModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}