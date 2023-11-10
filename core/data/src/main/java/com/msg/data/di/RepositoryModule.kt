package com.msg.data.di

import com.msg.data.repository.auth.AuthRepository
import com.msg.data.repository.auth.AuthRepositoryImpl
import com.msg.data.repository.lecture.LectureRepository
import com.msg.data.repository.lecture.LectureRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun bindLectureRepository(
        lectureRepositoryImpl: LectureRepositoryImpl
    ): LectureRepository
}