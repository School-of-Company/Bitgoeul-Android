package com.msg.network.util

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.msg.datastore.AuthTokenDataSource
import com.msg.model.remote.enumdatatype.Authority
import com.msg.network.BuildConfig
import com.msg.network.exception.NeedLoginException
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDateTime
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
        val ignorePath = listOf("/auth")
        val ignoreMethod = listOf("POST")
        val path = request.url.encodedPath
        val method = request.method

        ignorePath.forEachIndexed { index, s ->
            if (path.contains(s) && ignoreMethod[index] == method) {
                return chain.proceed(request)
            }
        }

        runBlocking {
            val refreshTime = dataSource.getRefreshTokenExp().toString()
            val accessTime = dataSource.getAccessTokenExp().toString()

            if (refreshTime == "") {
                return@runBlocking
            }

            if (currentTime.isAfter(refreshTime.toLocalDateTime())) {
                throw NeedLoginException()
            }

            //Re Issue Access Token
            if (currentTime.isAfter(accessTime.toLocalDateTime())) {
                val client = OkHttpClient()
                val refreshRequest = Request.Builder()
                    .url(BuildConfig.BASE_URL + "auth")
                    .patch(chain.request().body ?: RequestBody.Companion.create(null, byteArrayOf()))
                    .addHeader(
                        "RefreshToken",
                        dataSource.getRefreshToken().toString()
                    )
                    .build()
                val jsonParser = JsonParser()
                val response = client.newCall(refreshRequest).execute()
                if (response.isSuccessful) {
                    val token = jsonParser.parse(response.body!!.string()) as JsonObject
                    dataSource.setAccessToken(token["accessToken"].toString())
                    dataSource.setAccessTokenExp(token["accessExpiration"].toLocalDateTime())
                    dataSource.setRefreshToken(token["refreshToken"].toString())
                    dataSource.setRefreshTokenExp(token["refreshExpiration"].toLocalDateTime())
                } else throw NeedLoginException()
            }
            val accessToken = dataSource.getAccessToken()
            val refreshToken = dataSource.getRefreshToken()
            if (method == "DELETE") {
                builder.addHeader("RefreshToken", "Bearer $refreshToken")
            }
            builder.addHeader("Authorization", "Bearer $accessToken")
        }
        return chain.proceed(builder.build())
    }
}

fun LocalDateTime.isAfter(compare: LocalDateTime): Boolean {
    return this > compare
}
