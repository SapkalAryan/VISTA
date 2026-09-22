package com.vista.memoryos.di

import com.vista.memoryos.core.common.AppInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppInfo(): AppInfo {
        return AppInfo(
            appName = "VISTA",
            version = "1.0.0"
        )
    }
}