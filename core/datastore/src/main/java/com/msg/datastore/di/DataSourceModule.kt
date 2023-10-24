package com.msg.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.msg.datastore.AuthToken
import com.msg.datastore.AuthTokenSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideAuthTokenDataStore(
        @ApplicationContext context: Context,
        authTokenSerializer: AuthTokenSerializer
    ): DataStore<AuthToken> =
        DataStoreFactory.create(
            serializer = authTokenSerializer,
        ) {
            context.dataStoreFile("authToken.pb")
        }
}