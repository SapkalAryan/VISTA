package com.vista.memoryos.di

import com.vista.memoryos.data.repository.FileInventoryRepositoryImpl
import com.vista.memoryos.data.repository.FileStorageRepositoryImpl
import com.vista.memoryos.domain.repository.FileInventoryRepository
import com.vista.memoryos.domain.repository.FileStorageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindFileInventoryRepository(
        implementation: FileInventoryRepositoryImpl
    ): FileInventoryRepository

    @Binds
    @Singleton
    abstract fun bindFileStorageRepository(
        implementation: FileStorageRepositoryImpl
    ): FileStorageRepository
}