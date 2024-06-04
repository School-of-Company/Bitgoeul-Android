package com.msg.datastore.di

import com.msg.datastore.datasource.AuthTokenDataSource
import com.msg.datastore.datasource.AuthTokenDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    abstract fun bindAuthTokenDataSource(
        authTokenDataSourceImpl: AuthTokenDataSourceImpl
    ): AuthTokenDataSource
}