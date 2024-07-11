package com.msg.network.api

import com.msg.network.request.school.PostSchoolRequest
import com.msg.network.response.school.GetSchoolListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SchoolAPI {
    @GET("school")
    suspend fun getSchoolList(): GetSchoolListResponse

    @GET("school/name")
    suspend fun getSchoolName(): List<String>

    @POST("school")
    suspend fun postSchool(
        @Body body: PostSchoolRequest
    )
}