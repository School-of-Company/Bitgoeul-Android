package com.msg.network.di

import android.util.Log
import com.msg.network.BuildConfig
import com.msg.network.api.ActivityAPI
import com.msg.network.api.AuthAPI
import com.msg.network.api.CertificationAPI
import com.msg.network.api.FaqAPI
import com.msg.network.api.LectureAPI
import com.msg.network.api.UserAPI
import com.msg.network.util.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
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
 }