package com.msg.network.di

import com.msg.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppConfigModule {
    @Provides
    @Named("NATIVE_APP_KEY")
    fun provideNativeAppKey(): String = BuildConfig.NATIVE_APP_KEY
}