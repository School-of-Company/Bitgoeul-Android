package com.msg.network.util

import com.msg.common.exception.NeedLoginException
import com.msg.datastore.datasource.AuthTokenDataSource
import com.msg.network.BuildConfig
import com.msg.network.response.auth.AuthTokenResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataSource: AuthTokenDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val currentTime = System.currentTimeMillis().toLocalDateTime()
        val ignorePath = listOf("/auth", "/faq", "/club/name", "/school/name", "/company", "/university", "/government", "/school")
        val ignoreMethod = listOf("POST", "GET")
        val path = request.url.encodedPath
        val method = request.method

        run breaking@{
            ignorePath.forEachIndexed { index, s ->
                if (path.contains(s) && ignoreMethod.contains(method)) {
                    if (ignorePath.subList(4, 6).contains(s) && index == 0) return@breaking
                    return chain.proceed(request)
                }
            }
        }


        runBlocking {
            val refreshTime = dataSource.getRefreshTokenExp().first()
            val accessTime = dataSource.getAccessTokenExp().first()
            val accessToken = dataSource.getAccessToken().first()
            val refreshToken = dataSource.getRefreshToken().first()

            if (refreshTime.toString() == "") {
                return@runBlocking
            }

            if (currentTime != null && currentTime.isAfter(refreshTime.toLocalDateTime())) {
                throw NeedLoginException()
            }
            //Re Issue Access Token
            if (currentTime != null && currentTime.isAfter(accessTime.toLocalDateTime())) {
                val client = OkHttpClient()
                val refreshRequest = Request.Builder()
                    .url(BuildConfig.BASE_URL + "auth")
                    .patch(chain.request().body ?: RequestBody.Companion.create(null, byteArrayOf()))
                    .addHeader("RefreshToken", "Bearer $refreshToken")
                    .build()

                val moshi = Moshi.Builder().build()
                val adapter: JsonAdapter<AuthTokenResponse> = moshi.adapter(AuthTokenResponse::class.java)
                val response = client.newCall(refreshRequest).execute()
                if (response.isSuccessful) {
                    val token = adapter.fromJson(response.body!!.string()) ?: throw NeedLoginException()
                    dataSource.setAccessToken(token.accessExpiredAt).first()
                    dataSource.setAccessTokenExp(token.refreshExpiredAt).first()
                    dataSource.setRefreshToken(token.accessToken).first()
                    dataSource.setRefreshTokenExp(token.refreshToken).first()
                } else throw NeedLoginException()
            } else {
                builder.addHeader("Authorization", "Bearer $accessToken")
            }
            if (method == "DELETE") {
                builder.addHeader("RefreshToken", "Bearer $refreshToken")
            }
        }
        return chain.proceed(builder.build())
    }
}