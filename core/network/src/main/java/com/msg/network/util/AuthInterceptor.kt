package com.msg.network.util

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.msg.common.exception.NeedLoginException
import com.msg.datastore.datasource.AuthTokenDataSource
import com.msg.network.BuildConfig
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
        val ignorePath = listOf("/auth", "/faq")
        val ignoreMethod = listOf("POST", "GET")
        val path = request.url.encodedPath
        val method = request.method

        ignorePath.forEachIndexed { index, s ->
            if (path.contains(s) && ignoreMethod[index] == method) {
                return chain.proceed(request)
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

                val jsonParser = JsonParser()
                val response = client.newCall(refreshRequest).execute()
                if (response.isSuccessful) {
                    val token = jsonParser.parse(response.body!!.string()) as JsonObject
                    dataSource.setAccessToken(token["accessToken"].toString()).first()
                    dataSource.setAccessTokenExp(token["accessExpiration"].toString()).first()
                    dataSource.setRefreshToken(token["refreshToken"].toString()).first()
                    dataSource.setRefreshTokenExp(token["refreshExpiration"].toString()).first()
                } else throw NeedLoginException()
            } else {
                builder.addHeader("Authorization", "Bearer $accessToken")
            }
            if (method == "DELETE") {
                builder.addHeader("RefreshToken", "Bearer $refreshToken")
            }
            builder.addHeader("Authorization", "Bearer $accessToken")
        }
        return chain.proceed(builder.build())
    }
}