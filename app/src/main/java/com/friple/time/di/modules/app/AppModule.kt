package com.friple.time.di.modules.app

import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideMainString(): String = "Hello bitch!"
}