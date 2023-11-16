package com.msg.network.di

import com.msg.network.datasource.activity.ActivityDataSource
import com.msg.network.datasource.activity.ActivityDataSourceImpl
import com.msg.network.datasource.auth.AuthDataSource
import com.msg.network.datasource.auth.AuthDataSourceImpl
import com.msg.network.datasource.lecture.LectureDataSource
import com.msg.network.datasource.lecture.LectureDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindAuthDataSource(
        authDataSourceImpl: AuthDataSourceImpl
    ): AuthDataSource

    @Binds
    abstract fun bindLectureDataSource(
        lectureDataSourceImpl: LectureDataSourceImpl
    ): LectureDataSource

    @Binds
    abstract fun bindActivityDataSource(
        activityDataSourceImpl: ActivityDataSourceImpl
    ): ActivityDataSource
}