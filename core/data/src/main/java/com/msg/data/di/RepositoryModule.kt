package com.msg.data.di

import com.msg.data.repository.activity.ActivityRepository
import com.msg.data.repository.activity.ActivityRepositoryImpl
import com.msg.data.repository.auth.AuthRepository
import com.msg.data.repository.auth.AuthRepositoryImpl
import com.msg.data.repository.certification.CertificationRepository
import com.msg.data.repository.certification.CertificationRepositoryImpl
import com.msg.data.repository.club.ClubRepository
import com.msg.data.repository.club.ClubRepositoryImpl
import com.msg.data.repository.faq.FaqRepository
import com.msg.data.repository.faq.FaqRepositoryImpl
import com.msg.data.repository.lecture.LectureRepository
import com.msg.data.repository.lecture.LectureRepositoryImpl
import com.msg.data.repository.user.UserRepository
import com.msg.data.repository.user.UserRepositoryImpl
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

    @Binds
    abstract fun bindActivityRepository(
        activityRepositoryImpl: ActivityRepositoryImpl
    ): ActivityRepository

    @Binds
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun bindFaqRepository(
        faqRepositoryImpl: FaqRepositoryImpl
    ): FaqRepository

    @Binds
    abstract fun bindCertificationRepository(
        certificationRepositoryImpl: CertificationRepositoryImpl
    ): CertificationRepository

    @Binds
    abstract fun bindClubRepository(
        clubRepositoryImpl: ClubRepositoryImpl
    ): ClubRepository
}