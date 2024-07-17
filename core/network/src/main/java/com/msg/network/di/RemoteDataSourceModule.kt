package com.msg.network.di

import com.msg.network.datasource.activity.ActivityDataSource
import com.msg.network.datasource.activity.ActivityDataSourceImpl
import com.msg.network.datasource.admin.AdminDataSource
import com.msg.network.datasource.admin.AdminDataSourceImpl
import com.msg.network.datasource.auth.AuthDataSource
import com.msg.network.datasource.auth.AuthDataSourceImpl
import com.msg.network.datasource.certification.CertificationDataSource
import com.msg.network.datasource.certification.CertificationDataSourceImpl
import com.msg.network.datasource.club.ClubDataSource
import com.msg.network.datasource.club.ClubDataSourceImpl
import com.msg.network.datasource.company.CompanyDataSource
import com.msg.network.datasource.company.CompanyDataSourceImpl
import com.msg.network.datasource.email.EmailDataSource
import com.msg.network.datasource.email.EmailDataSourceImpl
import com.msg.network.datasource.faq.FaqDataSource
import com.msg.network.datasource.faq.FaqDataSourceImpl
import com.msg.network.datasource.lecture.LectureDataSource
import com.msg.network.datasource.lecture.LectureDataSourceImpl
import com.msg.network.datasource.map.MapDataSource
import com.msg.network.datasource.map.MapDataSourceImpl
import com.msg.network.datasource.post.PostDataSource
import com.msg.network.datasource.post.PostDataSourceImpl
import com.msg.network.datasource.school.SchoolDataSource
import com.msg.network.datasource.school.SchoolDataSourceImpl
import com.msg.network.datasource.user.UserDataSource
import com.msg.network.datasource.user.UserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
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

    @Binds
    abstract fun bindUserDataSource(
        userDataSourceImpl: UserDataSourceImpl
    ): UserDataSource

    @Binds
    abstract fun bindFaqDataSource(
        faqDataSourceImpl: FaqDataSourceImpl
    ): FaqDataSource

    @Binds
    abstract fun bindCertificationDataSource(
        certificationDataSourceImpl: CertificationDataSourceImpl
    ): CertificationDataSource

    @Binds
    abstract fun bindClubDataSource(
        clubDataSourceImpl: ClubDataSourceImpl
    ): ClubDataSource

    @Binds
    abstract fun bindAdminDataSource(
        adminDataSourceImpl: AdminDataSourceImpl
    ): AdminDataSource

    @Binds
    abstract fun bindPostDataSource(
        postDataSourceImpl: PostDataSourceImpl
    ): PostDataSource

    @Binds
    abstract fun bindEmailDataSource(
        emailDataSourceImpl: EmailDataSourceImpl
    ): EmailDataSource

    @Binds
    abstract fun bindSchoolDataSource(
        schoolDataSourceImpl: SchoolDataSourceImpl
    ): SchoolDataSource

    @Binds
    abstract fun bindCompanyDataSource(
        companyDataSourceImpl: CompanyDataSourceImpl
    ): CompanyDataSource

    @Binds
    abstract fun bindMapDataSource(
        mapDataSourceImpl: MapDataSourceImpl
    ): MapDataSource
}