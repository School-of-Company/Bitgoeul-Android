package com.msg.network.di

import android.util.Log
import com.msg.network.BuildConfig
import com.msg.network.api.ActivityAPI
import com.msg.network.api.AdminAPI
import com.msg.network.api.AuthAPI
import com.msg.network.api.CertificationAPI
import com.msg.network.api.ClubAPI
import com.msg.network.api.EmailAPI
import com.msg.network.api.FaqAPI
import com.msg.network.api.LectureAPI
import com.msg.network.api.PostAPI
import com.msg.network.api.UserAPI
import com.msg.network.util.AuthInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message -> Log.v("HTTP", message) }
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkhttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshiInstance(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
        ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Provides
    fun provideAuthAPI(retrofit: Retrofit): AuthAPI =
        retrofit.create(AuthAPI::class.java)

    @Provides
    fun provideLectureAPI(retrofit: Retrofit): LectureAPI =
        retrofit.create(LectureAPI::class.java)

    @Provides
    fun provideActivityAPI(retrofit: Retrofit): ActivityAPI =
        retrofit.create(ActivityAPI::class.java)

    @Provides
    fun provideUserAPI(retrofit: Retrofit): UserAPI =
        retrofit.create(UserAPI::class.java)

    @Provides
    fun provideFaqAPI(retrofit: Retrofit): FaqAPI =
        retrofit.create(FaqAPI::class.java)

    @Provides
    fun provideCertificationAPI(retrofit: Retrofit): CertificationAPI =
        retrofit.create(CertificationAPI::class.java)

    @Provides
    fun provideClubAPI(retrofit: Retrofit): ClubAPI =
        retrofit.create(ClubAPI::class.java)

    @Provides
    fun provideAdminAPI(retrofit: Retrofit): AdminAPI =
        retrofit.create(AdminAPI::class.java)

    @Provides
    fun providePostAPI(retrofit: Retrofit): PostAPI =
        retrofit.create(PostAPI::class.java)

    @Provides
    fun provideEmailAPI(retrofit: Retrofit): EmailAPI =
        retrofit.create(EmailAPI::class.java)
}